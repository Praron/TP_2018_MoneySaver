package com.example.lumberjacks.moneysaver

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.third_step_fragment.view.*
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.top_spending_list_item.view.*


class ThirdStepFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.third_step_fragment, container, false).apply {
            val adapter = ArrayAdapter(activity, R.layout.spinner_item,
                    arrayListOf("this day", "this week", "this month", "this year"))
            adapter.setDropDownViewResource(R.layout.spinner_item)
            total_spent_spinner.adapter = adapter
            total_spent_spinner.setSelection(2)  // TODO: Dude, we need to make something with this ugly Pairs and other, I think.
            // )

            val data = arrayListOf(Pair("Food", 3200), Pair("Clothes", 1100), Pair("Electronics", 500), Pair("Other", 30))
            top_spends_list.adapter = TopSpentAdapter(inflater, data)

            other_spending_button.setOnClickListener {
                activity?.supportFragmentManager?.apply {
                    popBackStack("first_step", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    inTransaction {
                        replace(R.id.main_fragment, FirstStepFragment())
                    }
                }
            }

            statistics_button.setOnClickListener {
                activity?.supportFragmentManager?.apply {
                    inTransaction {
                        replace(R.id.main_fragment, StatisticsFragment())
                        addToBackStack(null)
                    }
                }
            }
        }
    }

    class TopSpentAdapter(private val inflater : LayoutInflater, private val data: ArrayList<Pair<String, Int>>) : BaseAdapter() {
        override fun getItemId(position: Int): Long = position.toLong()

        override fun getCount(): Int = data.size

        override fun getItem(position: Int): Any = data[position]

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            return inflater.inflate(R.layout.top_spending_list_item, parent, false).apply {
                this.category_name.text = data[position].first
                this.category_total_spent.text = data[position].second.toString()
            }
        }

    }

}
