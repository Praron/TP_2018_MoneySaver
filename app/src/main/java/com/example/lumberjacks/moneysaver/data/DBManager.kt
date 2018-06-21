package com.example.lumberjacks.moneysaver.data

import com.example.lumberjacks.moneysaver.Category
import com.example.lumberjacks.moneysaver.MainActivity
import com.example.lumberjacks.moneysaver.utils.DateTypes
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DBManager(var dbHelper: DatabaseOpenHelper = DatabaseOpenHelper(MainActivity.instance)):  DBSource {
    override fun getAllCategory(): List<Category> = dbHelper.use{
        select("Categories").parseList(object : MapRowParser<Category> {
            override fun parseRow(columns: Map<String, Any?>): Category {

                val id = columns.getValue("id") as Long
                val name = columns.getValue("name") as String
                var description = ""
                if (columns.getValue("description") != null){
                    description = columns.getValue("description") as String
                }

                return Category(id=id, name = name, description = description)
            }
        })
    }

    override fun saveSpendingInCategory(clickedCategory: Category, price: Int) : Boolean = dbHelper.use {
        val insertedResult = insert("Spendings",
                "price" to price,
                "spend_datetime" to System.currentTimeMillis()/1000,
                "category_id" to clickedCategory.id
        )
        return@use insertedResult!= -1L
    }

    override fun createNewCategory(categoryName: String, categoryDescription: String) : Boolean = dbHelper.use {
        val insertedResult = insert("Categories",
                "name" to categoryName,
                "description" to categoryDescription
        )
        return@use insertedResult != -1L
    }

    override fun getSumSpendingByTime(byTime: DateTypes): Long = dbHelper.use {
        select("Spendings", "sum(price) AS 'Total Sum'")
                .whereArgs("spend_datetime BETWEEN (strftime('%s','now')-86400) AND (strftime('%s','now'))")
                .parseSingle(object : MapRowParser<Long>{
                    override fun parseRow(columns: Map<String, Any?>): Long {
                        return columns.getValue("Total Sum") as Long
                    }
                })
    }

    override fun getAllSpendingByCategoryByTime(byTime: DateTypes): List<Pair<String, Long>> = dbHelper.use {
            select("spendings as s join categories as c on (s.category_id = c.id)",
                    "name, sum(price) as 'Total'")
                    .whereArgs("spend_datetime BETWEEN (strftime('%s','now')-86400) AND (strftime('%s','now'))").groupBy("c.name")
                    .parseList(object : MapRowParser<Pair<String, Long>> {
                        override fun parseRow(columns: Map<String, Any?>): Pair<String, Long> {
                            val name = columns.getValue("name") as String
                            val total = columns.getValue("Total") as Long
                            return Pair(name, total)
                        }
                    })
        }
}