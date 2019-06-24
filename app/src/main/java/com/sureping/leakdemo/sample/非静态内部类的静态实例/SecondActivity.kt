package com.sureping.leakdemo.sample.非静态内部类的静态实例

import android.os.Bundle
import com.sureping.leakdemo.R
import com.sureping.leakdemo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*

class SecondActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        button.setOnClickListener {
            createInnerClass()
            finish()
        }
    }
    private fun createInnerClass(){
        class InnerClass
        inner = InnerClass()
    }
    companion object {
        private var inner : Any? = null
    }
}