package com.example.lumberjacks.moneysaver

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.first_step_fragment.*
import kotlinx.android.synthetic.main.second_step_fragment.*
import android.support.v7.widget.LinearLayoutManager

class SecondStepFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.second_step_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onWhatText.text = arguments.getInt("price").toString()

//        val adapter = ArrayAdapter(activity, R.layout.categories_list_item, arrayOf("Food", "Clothes", "Other"))
//        categoriesList.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(activity)
//        categoriesRecyclerView.layoutManager = linearLayoutManager

    }
}