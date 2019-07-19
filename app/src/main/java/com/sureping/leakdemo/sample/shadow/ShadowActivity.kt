package com.sureping.leakdemo.sample.shadow

import android.os.Bundle
import com.sureping.leakdemo.R
import com.sureping.leakdemo.base.BaseActivity

class ShadowActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shadow)
    }
}