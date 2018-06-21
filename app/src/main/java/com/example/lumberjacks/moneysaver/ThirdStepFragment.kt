package com.example.lumberjacks.moneysaver

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.third_step_fragment.view.*
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import com.example.lumberjacks.moneysaver.data.DBManager
import com.example.lumberjacks.moneysaver.utils.DateTypes
import kotlinx.android.synthetic.main.top_spending_list_item.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class ThirdStepFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.third_step_fragment, container, false).apply {
            val adapter = ArrayAdapter(activity, R.layout.spinner_item,
                    R.id.spinner_item_text, resources.getStringArray(R.array.spinner_values))
            adapter.setDropDownViewResource(R.layout.spinner_item)
            total_spent_spinner.adapter = adapter

            total_spent_spinner.setSelection(0)  // TODO: Dude, we need to make something with this ugly Pairs and other, I think.
            doAsync(executorService = MainActivity.dbExecutor) {
                val dbSpending = DBManager().getSumSpendingByTime(DateTypes.DAY)
                uiThread {
                    total_spent.text = dbSpending.toInt().toString()
                }
            }

            doAsync(executorService = MainActivity.dbExecutor) {
                val dbSpending = DBManager().getAllSpendingByCategoryByTime(DateTypes.DAY)
                uiThread {
                    top_spends_list.adapter = TopSpentAdapter(inflater, dbSpending)
                }
            }

//            val data = arrayListOf(Pair("Food", 3200), Pair("Clothes", 1100), Pair("Electronics", 500), Pair("Other", 30))
//

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

    class TopSpentAdapter(private val inflater: LayoutInflater, private val data: List<Pair<String, Double>>) : BaseAdapter() {
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
