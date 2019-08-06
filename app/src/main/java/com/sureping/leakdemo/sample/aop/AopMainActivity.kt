package com.sureping.leakdemo.sample.aop

import android.os.Bundle
import android.util.Log
import com.sureping.leakdemo.base.BaseActivity
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy

/**
 * author pisa
 * date  2019/7/23
 * version 1.0
 * effect :
 */
class AopMainActivity : BaseActivity() ,ILogin{
    override fun isLogin() {
        Log.e("aop","isLogin")
    }

    lateinit var proxy: ILogin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        proxy= Proxy.newProxyInstance(
            this.classLoader,
            arrayOf(ILogin::class.java),
            InvocationHandler { proxy, method, args ->
                Log.e("aop","isProxy")
//                if release this code ，AopMainActivity&isLogin().isLogin()
//                method.invoke(this)
            }
        ) as ILogin
        proxy.isLogin()


    }
}