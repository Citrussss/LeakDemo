package com.sureping.leakdemo.base.recyclerview

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

class RecyclerBaseHolder(val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root)