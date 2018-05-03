package com.example.lumberjacks.moneysaver

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.second_step_fragment.*

class SecondStepFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.second_step_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val price = arguments!!.getInt("price")
        onWhatText.text = price.toString()

        val linearLayoutManager = LinearLayoutManager(activity)

        categoriesRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = CategoryRecyclerAdapter(arrayListOf(
                    Category("Food", "food"),
                    Category("Clothes"),
                    Category("Other", "different shit")
            )
            , {clickedCategory -> saveSpendings(clickedCategory, price)})}
        }

    private fun saveSpendings(clickedCategory: Category, price: Int) {
        val spending = Spending(clickedCategory.name, price)
        spending.save(this.context!!)
        val toast = Toast.makeText(this.context,
                "${clickedCategory.name} - $price SAVED",
                Toast.LENGTH_SHORT)
        toast.show()
    }
}