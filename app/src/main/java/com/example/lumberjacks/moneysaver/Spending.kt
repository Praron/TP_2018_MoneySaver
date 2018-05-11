package com.example.lumberjacks.moneysaver

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.spending_list_item.view.*
import java.util.*

data class Spending(val category: Category, val value : Int, val date : Date = Calendar.getInstance().time)


class SpendingsAdapter(private val spendings: List<Spending>, private val spendingClick: (Spending) -> Unit) : RecyclerView.Adapter<SpendingsAdapter.SpendingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpendingsAdapter.SpendingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.spending_list_item, parent, false)
        return SpendingViewHolder(view, spendingClick)
    }

    override fun onBindViewHolder(holder: SpendingViewHolder, position: Int) {
        holder.bindSpending(spendings[position])
    }

    override fun getItemCount(): Int = spendings.size

    inner class SpendingViewHolder(itemView: View, private val itemClick: (Spending) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bindSpending(spending: Spending) {
            with(itemView) {
                with(spending) {
                    setOnClickListener { itemClick(this) }
                    spending_name.text = category.name
                    spending_value.text = value.toString()
                    spending_date.text = date.toString()
                }
            }
        }
    }
}
