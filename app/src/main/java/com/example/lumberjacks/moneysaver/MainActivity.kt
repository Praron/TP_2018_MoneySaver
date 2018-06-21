package com.example.lumberjacks.moneysaver

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.widget.Toast
import com.example.lumberjacks.moneysaver.R.layout.activity_main
import android.os.StrictMode
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        instance = this
        dbExecutor = Executors.newSingleThreadExecutor()!!
        networkExecutor = Executors.newSingleThreadExecutor()!!
        StrictMode.enableDefaults() //Info for leaks, io stats etc
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        supportFragmentManager.inTransaction {
            replace(R.id.main_fragment, FirstStepFragment())
        }
    }

    companion object {
        lateinit var instance : MainActivity private set
        lateinit var dbExecutor: ExecutorService
        lateinit var networkExecutor: ExecutorService
    }

}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun Fragment.toast(message: CharSequence) =
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()