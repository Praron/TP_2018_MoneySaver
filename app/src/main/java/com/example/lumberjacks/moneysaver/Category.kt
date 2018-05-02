package com.example.lumberjacks.moneysaver

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.categories_list_item.view.*

data class Category (val name: String, val description : String = "")


class CategoryRecyclerAdapter(val categories: List<Category>, val categoryClick: (Category) -> Unit) : RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryRecyclerAdapter.CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_list_item, parent, false)
        return CategoryViewHolder(view, categoryClick)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindCategory(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(itemView: View, itemClick: (Category) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bindCategory(category: Category) {
            with(category) {
                itemView.setOnClickListener { categoryClick(this) }
                itemView.category_name.text = name
                itemView.category_description.text = description
            }
        }
    }
}
