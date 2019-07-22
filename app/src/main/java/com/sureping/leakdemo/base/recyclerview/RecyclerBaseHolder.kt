package com.sureping.leakdemo.base.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class RecyclerBaseHolder<V:ViewDataBinding>(val dataBinding: V) : androidx.recyclerview.widget.RecyclerView.ViewHolder(dataBinding.root)