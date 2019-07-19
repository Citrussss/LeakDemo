package com.sureping.leakdemo.base

import android.support.v7.app.AppCompatActivity
import com.sureping.leakdemo.LeakApplication

abstract class BaseActivity:AppCompatActivity() {


    override fun onDestroy() {
        super.onDestroy()
        LeakApplication.getRefWatcher().watch(this)
    }
}