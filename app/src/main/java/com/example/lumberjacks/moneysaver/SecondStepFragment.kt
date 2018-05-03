package com.example.lumberjacks.moneysaver

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DividerItemDecoration.VERTICAL
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.add_category_dialog.*
import kotlinx.android.synthetic.main.second_step_fragment.*


class SecondStepFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.second_step_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(activity)

        categories_recycler_view.addItemDecoration(DividerItemDecoration(activity!!.applicationContext, VERTICAL))

        categories_recycler_view.apply {
            layoutManager = linearLayoutManager
            adapter = CategoryRecyclerAdapter(arrayListOf(
                    Category("Food", "food"),
                    Category("Clothes"),
                    Category("Other", "different shit")
            )
            , {clickedCategory -> toast("${clickedCategory.name} Clicked")})
        }

        add_category_button.setOnClickListener {createNewCategory()}
    }

    private fun createNewCategory() {
        val dialogView = LayoutInflater.from(activity).inflate(R.layout.add_category_dialog, null)
        val alertDialogBuilderUserInput = AlertDialog.Builder(activity as Context).apply {
            setView(dialogView)
            setCancelable(true)
            setPositiveButton("Create") { dialogBox, id ->
                dialogBox.dismiss()
                val newCategoryName = category_name_input.text.toString()
                val newCategoryDescription = category_description_input.text.toString()
            }
            setNegativeButton("Cancel") { dialogBox, id -> dialogBox.cancel() }
        }

        val alertDialogAndroid = alertDialogBuilderUserInput.create().apply {
            show()
            val createButton = getButton(AlertDialog.BUTTON_POSITIVE).apply {isEnabled = false}
            category_name_input.afterTextChanged { newCategory ->
                createButton.isEnabled = !(newCategory.isBlank())
            }
        }
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}