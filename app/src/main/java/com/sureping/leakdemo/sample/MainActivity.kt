package com.sureping.leakdemo.sample

import android.graphics.Bitmap
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.core.net.toFile
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import com.sureping.leakdemo.R
import com.sureping.leakdemo.base.BaseActivity
import com.sureping.leakdemo.base.recyclerview.Inflate
import com.sureping.leakdemo.base.recyclerview.RecyclerInflate
import com.sureping.leakdemo.base.recyclerview.RecyclerBaseAdapter
import com.sureping.leakdemo.sample.aop.AopMainActivity
import com.sureping.leakdemo.sample.exo.ExoPlayerActivity
import com.sureping.leakdemo.sample.exo_rc.ExoRcActivity
import com.sureping.leakdemo.sample.navigation.NavigationActivity
import com.sureping.leakdemo.sample.shadow.ShadowActivity
import com.sureping.leakdemo.sample.workmanager.NetWorkManagerActivity
import com.sureping.leakdemo.sample.非静态内部类的静态实例.SecondActivity
import com.sureping.leakdemo.sample.非静态内部类的静态实例.SecondJavaActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Proxy
import java.net.URI
import kotlin.collections.ArrayList
class MainActivity : BaseActivity() {
    val adapter = RecyclerBaseAdapter<Inflate<ViewDataBinding>>()
    val bitmap:Bitmap = Bitmap.createBitmap(1920,1080,Bitmap.Config.ARGB_8888);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        val list = ArrayList<Inflate<ViewDataBinding>>()
        list.add(MainItemEntity("非静态内部类的静态实例:kotlin", SecondActivity::class.java))
        list.add(MainItemEntity("非静态内部类的静态实例:java", SecondJavaActivity::class.java))
        list.add(MainItemEntity("阴影样式", ShadowActivity::class.java))
        list.add(MainItemEntity("Exo播放器", ExoPlayerActivity::class.java))
        list.add(MainItemEntity("Exo结合RecyclerView", ExoRcActivity::class.java))
        list.add(MainItemEntity("使用WorkManager监听网络变化", NetWorkManagerActivity::class.java))
        list.add(MainItemEntity("NavigationActivity", NavigationActivity::class.java))
        list.add(MainItemEntity("AopMainActivity", AopMainActivity::class.java))

        adapter.setData(list)
    }
}