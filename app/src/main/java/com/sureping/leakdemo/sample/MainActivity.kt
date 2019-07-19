package com.sureping.leakdemo.sample

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.sureping.leakdemo.R
import com.sureping.leakdemo.base.BaseActivity
import com.sureping.leakdemo.base.recyclerview.RecyclerInflate
import com.sureping.leakdemo.base.recyclerview.RecyclerBaseAdapter
import com.sureping.leakdemo.sample.exo.ExoPlayerActivity
import com.sureping.leakdemo.sample.shadow.ShadowActivity
import com.sureping.leakdemo.sample.非静态内部类的静态实例.SecondActivity
import com.sureping.leakdemo.sample.非静态内部类的静态实例.SecondJavaActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity() {
    val adapter = RecyclerBaseAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        val list = ArrayList<RecyclerInflate>()
        list.add(MainItemEntity("非静态内部类的静态实例:kotlin", SecondActivity::class.java))
        list.add(MainItemEntity("非静态内部类的静态实例:java", SecondJavaActivity::class.java))
        list.add(MainItemEntity("阴影样式", ShadowActivity::class.java))
        list.add(MainItemEntity("Exo播放器",ExoPlayerActivity::class.java))
        adapter.setData(list)
    }
}