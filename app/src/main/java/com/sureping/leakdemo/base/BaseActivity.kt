package com.sureping.leakdemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sureping.leakdemo.LeakApplication
import com.sureping.leakdemo.sample.aop.AopInvocationHandler
import com.sureping.leakdemo.sample.aop.ILogin
import java.lang.reflect.Proxy

abstract class BaseActivity:AppCompatActivity() {


    override fun onDestroy() {
        super.onDestroy()
        LeakApplication.getRefWatcher().watch(this)
    }
}