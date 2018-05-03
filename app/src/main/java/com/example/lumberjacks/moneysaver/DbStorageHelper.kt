package com.example.lumberjacks.moneysaver

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*


class DbStorageHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "mydb"){
    companion object {
        private var instance: DbStorageHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DbStorageHelper {
            if (instance == null) {
                instance = DbStorageHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable("spendings", true,
                "price" to INTEGER,
                "category_name" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable("spendings", true)
    }

    fun put(categoryName: String, price: Int) {
        val db = writableDatabase
        db.insert("spendings", "category_name" to categoryName, "price" to price)
        db.close()
    }


}