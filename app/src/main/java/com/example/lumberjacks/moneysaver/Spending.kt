package com.example.lumberjacks.moneysaver

import android.content.Context


class Spending(private val categoryName: String, private val price: Int) {

    public fun save(context: Context){
        val dbHelper =  DbStorageHelper(context)
        dbHelper.put(this.categoryName, this.price)
    }

}