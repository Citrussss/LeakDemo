package com.sureping.leakdemo.base.recyclerview

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import java.lang.RuntimeException
import java.util.*
import kotlin.collections.ArrayList

open class RecyclerBaseAdapter<I : Inflate<out ViewDataBinding>> : RecyclerView.Adapter<RecyclerBaseHolder<out ViewDataBinding>>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerBaseHolder<out ViewDataBinding> {
        val inflate = typePool.get(p1)
        val dataBinding = inflate?.inflate(p0.context, null, p0, false)
        return RecyclerBaseHolder(dataBinding!!)
    }

    override fun onBindViewHolder(holder: RecyclerBaseHolder<out ViewDataBinding>, position: Int) {
        val entity = list[position]
        entity.inflate(holder.dataBinding.root.context,holder.dataBinding,null,false)
    }

    private val list :MutableList<I> = ArrayList()
    private val typePool = WeakHashMap<Int,I>()
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerBaseHolder {
//
//    }

    override fun getItemCount(): Int {
        return list.size
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
    fun setData(list:List<I>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
    fun getData():MutableList<I>{
        return list;
    }
}