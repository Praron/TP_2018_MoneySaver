package com.example.lumberjacks.moneysaver.data

import com.example.lumberjacks.moneysaver.Category
import com.example.lumberjacks.moneysaver.MainActivity
import com.example.lumberjacks.moneysaver.Spending
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
                "date" to System.currentTimeMillis()/1000,
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

    override fun getAllSpendingByTime(byTime: DateTypes): List<Spending> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllSpendingByCategoryByTime(byTime: Int, byCategory: Category): List<Spending> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}