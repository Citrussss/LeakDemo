package com.sureping.leakdemo.base.recyclerview

import android.databinding.ViewDataBinding
import com.sureping.leakdemo.R

abstract class RecyclerInflate : Inflate<ViewDataBinding> {
    override var dataBinding: ViewDataBinding?=null
    var name : String = "Test$this"
}