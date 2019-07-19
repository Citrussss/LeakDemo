//package com.sureping.leakdemo.sample.gys
//
//import android.os.Bundle
//import android.support.v7.widget.LinearLayoutManager
//import com.sureping.leakdemo.base.BaseActivity
//import kotlinx.android.synthetic.main.activity_gsy_list_video2.*
//import com.shuyu.gsyvideoplayer.utils.GSYVideoHelper
//import android.R
//import com.shuyu.gsyvideoplayer.GSYVideoManager
//
//
///**
// * author pisa
// * date  2019/7/19
// * version 1.0
// * effect :
// */
//class GsyActivity : BaseActivity() {
//    val adapter = GsyAdapter()
//    lateinit var gsySmallVideoHelperBuilder: GSYVideoHelper.GSYVideoHelperBuilder
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(com.sureping.leakdemo.R.layout.activity_gsy_list_video2)
//        videoList.adapter = adapter
//        videoList.layoutManager = LinearLayoutManager(this)
//        val list = ArrayList<GsyEntity>()
//        list.add(GsyEntity())
//        list.add(GsyEntity())
//        list.add(GsyEntity())
//        list.add(GsyEntity())
//        adapter.setData(list)
//        val position = GSYVideoManager.instance().playPosition
//
//    }
//}