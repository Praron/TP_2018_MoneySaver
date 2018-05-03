package com.example.lumberjacks.moneysaver

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.second_step_fragment.*

class SecondStepFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.second_step_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onWhatText.text = arguments!!.getInt("price").toString()

        val linearLayoutManager = LinearLayoutManager(activity)

        categoriesRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = CategoryRecyclerAdapter(arrayListOf(
                    Category("Food", "food"),
                    Category("Clothes"),
                    Category("Other", "different shit")
            )
            , {clickedCategory -> toast("${clickedCategory.name} Clicked")})
        }
    }
}