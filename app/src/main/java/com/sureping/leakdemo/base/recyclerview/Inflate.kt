package com.sureping.leakdemo.base.recyclerview

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sureping.leakdemo.BR

interface Inflate<DB:ViewDataBinding> {
    var dataBinding: ViewDataBinding?
    fun inflate(context:Context,dataBinding: ViewDataBinding?,viewGroup: ViewGroup?,attachToParent:Boolean):ViewDataBinding{
        this.dataBinding = dataBinding ?: DataBindingUtil.inflate<DB>(LayoutInflater.from(context), getLayoutId(), viewGroup, attachToParent)
        this.dataBinding?.setVariable(BR.vm,this)
        Log.i("inflate",this.toString())
        return this.dataBinding!!
    }
    fun getLayoutId():Int
}