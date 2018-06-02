package com.example.lumberjacks.moneysaver

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.statistics_fragment.view.*

class StatisticsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.statistics_fragment, container, false).apply {
            statics_tab_host.apply {
                setup()
                addTab(newTabSpec("spends_list").setIndicator("My spendings").setContent(R.id.spendings_list_view))
                addTab(newTabSpec("statistic").setIndicator("Statistic").setContent(R.id.spendings_graph_view))
                setCurrentTabByTag("spends_list")
            }

            spendings_list_view.apply {
                addItemDecoration(DividerItemDecoration(activity!!.applicationContext, DividerItemDecoration.VERTICAL))
                layoutManager = LinearLayoutManager(activity)
                adapter = SpendingsAdapter(arrayListOf(
                        Spending(Category(1,"Food", "food"), 50),
                        Spending(Category(2,"Food", "food"), 100),
                        Spending(Category(3,"Food", "food"), 120),
                        Spending(Category(4,"Clothes"), 1500),
                        Spending(Category(5,"Food", "food"), 500),
                        Spending(Category(6,"Clothes"), 500),
                        Spending(Category(7,"Other", "different shit"), 1000)
                )
                        , { }
                )
            }
        }
    }
}