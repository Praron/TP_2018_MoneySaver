package com.example.lumberjacks.moneysaver

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

class DatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyDatabase", null, 1) {
    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
                "Spendings", true,
                "id" to INTEGER + PRIMARY_KEY + UNIQUE,
                "amount" to INTEGER,
                "category_id" to INTEGER
        )
        db.createTable("Categories", true,
                "id" to INTEGER + PRIMARY_KEY + UNIQUE,
                "name" to TEXT,
                "description" to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable("Spendings", true)
        db.dropTable("Categories", true)
    }
}

// Access property for Context
val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(getApplicationContext())