package com.sureping.leakdemo.base

import androidx.appcompat.app.AppCompatActivity
import com.sureping.leakdemo.LeakApplication

abstract class BaseActivity:AppCompatActivity() {


    override fun onDestroy() {
        super.onDestroy()
        LeakApplication.getRefWatcher().watch(this)
    }
}