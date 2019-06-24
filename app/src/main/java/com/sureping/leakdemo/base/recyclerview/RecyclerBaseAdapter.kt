package com.sureping.leakdemo.base.recyclerview

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.ViewGroup
import java.lang.RuntimeException
import java.util.*
import kotlin.collections.ArrayList

class RecyclerBaseAdapter : RecyclerView.Adapter<RecyclerBaseHolder>() {
    private val list :MutableList<Inflate<ViewDataBinding>> = ArrayList()
    private val typePool = WeakHashMap<Int,Inflate<ViewDataBinding>>()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerBaseHolder {
        val inflate = typePool.get(p1)
        val dataBinding = inflate?.inflate(p0.context, null, p0, false)
        return RecyclerBaseHolder(dataBinding!!)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: RecyclerBaseHolder, p1: Int) {
        val entity = list.get(p1)
        entity.inflate(p0.dataBinding.root.context,p0.dataBinding,null,false)
    }

    override fun getItemViewType(position: Int): Int {
        if (position>=list.size)throw RuntimeException("NOT FOUND TYPE $position")
        else {
            val inflate = list.get(position)
            val layoutId = inflate.getLayoutId()
            val inflateMillor = typePool.get(layoutId)
            if (inflateMillor==null)typePool.put(layoutId,inflate)
            return layoutId
        }
    }

    /**
     * 更换数据源
     */
    fun setData(list:List<Inflate<ViewDataBinding>>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}