package com.example.lumberjacks.moneysaver.data

import com.example.lumberjacks.moneysaver.Category
import com.example.lumberjacks.moneysaver.Spending
import com.example.lumberjacks.moneysaver.utils.DateTypes

interface DBSource {
    fun saveSpendingInCategory(clickedCategory: Category, price: Int) : Boolean

    fun createNewCategory(categoryName: String, categoryDescription: String) : Boolean

    fun getAllCategory() : List<Category>

    fun getSumSpendingByTime(byTime: DateTypes) : Long

    fun getAllSpendingByCategoryByTime(byTime: DateTypes) : List<Pair<String, Long>>

}