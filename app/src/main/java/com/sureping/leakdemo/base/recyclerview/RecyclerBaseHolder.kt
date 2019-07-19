package com.sureping.leakdemo.base.recyclerview

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

class RecyclerBaseHolder<V:ViewDataBinding>(val dataBinding: V) : RecyclerView.ViewHolder(dataBinding.root)