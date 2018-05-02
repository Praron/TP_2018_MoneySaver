package com.example.lumberjacks.moneysaver

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.first_step_fragment.*

class FirstStepFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.first_step_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        nextStepButton.setOnClickListener {
            if (priceEditBox.text.isEmpty()) {
                showWarning("Enter price")
            } else {
                activity?.supportFragmentManager?.inTransaction {

                    val secondFragment = SecondStepFragment().withArgs {
                        putInt("price", priceEditBox.text.toString().toInt())
                    }
                    replace(R.id.main_fragment, secondFragment)
                    addToBackStack(null)
                }
            }
        }

    }

    private fun showWarning(message: Any) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Warning")
                .setCancelable(false)
                .setTitle(message.toString())
                .setNegativeButton("Ok", DialogInterface.OnClickListener { dialog, whichButton ->
                })
        val dialog = builder.create()
        dialog.show()
    }
}

inline fun <T : Fragment> T.withArgs(argsBuilder: Bundle.() -> Unit): T =
        this.apply { arguments = Bundle().apply(argsBuilder) }