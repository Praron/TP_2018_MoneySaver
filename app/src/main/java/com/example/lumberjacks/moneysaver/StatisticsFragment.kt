package com.example.lumberjacks.moneysaver

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.statistics_fragment.view.*

class StatisticsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.statistics_fragment, container, false).apply {
            statics_tab_host.apply {
                setup()
                addTab(newTabSpec("spends_list").setIndicator("My spendings").setContent(R.id.tvTab1))
                addTab(newTabSpec("statistic").setIndicator("Statistic").setContent(R.id.tvTab2))
                setCurrentTabByTag("spends_list")
            }
        }
    }
}