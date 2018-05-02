package com.example.lumberjacks.moneysaver

import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

data class Category (val name: String, val description : String = "")


class CategoryRecyclerAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun onBindViewHolder(holder:, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): {
    }

    override fun getItemCount(): Int {
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
