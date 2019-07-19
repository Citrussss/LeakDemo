//package com.sureping.leakdemo.sample.gys
//
//import android.databinding.ViewDataBinding
//import android.view.View
//import com.sureping.leakdemo.base.recyclerview.RecyclerBaseAdapter
//import com.sureping.leakdemo.base.recyclerview.RecyclerBaseHolder
//import android.view.View.GONE
//import com.sureping.leakdemo.R
//
//
///**
// * author pisa
// * date  2019/7/19
// * version 1.0
// * effect :
// */
//class GsyAdapter : RecyclerBaseAdapter<GsyEntity>() {
//    companion object {
//        public var TAG: String = "GsyAdapter"
//    }
//
//    override fun onBindViewHolder(holder: RecyclerBaseHolder<out ViewDataBinding>, position: Int) {
//        super.onBindViewHolder(holder, position)
//        if (holder.dataBinding is com.sureping.leakdemo.databinding.HolderVideoItemBinding) {
//            holder.dataBinding.detailPlayer.setUpLazy(holder.dataBinding.root.context.getString(R.string.media_url_mp4), true, null, null, "这是title")
//            //增加title
//            holder.dataBinding.detailPlayer.getTitleTextView().setVisibility(View.GONE)
//            //设置返回键
//            holder.dataBinding.detailPlayer.getBackButton().setVisibility(View.GONE)
//            //设置全屏按键功能
//            holder.dataBinding.detailPlayer.getFullscreenButton().setOnClickListener {
//                holder.dataBinding.detailPlayer.startWindowFullscreen(holder.dataBinding.root.context, false, true) }
//            //防止错位设置
//            holder.dataBinding.detailPlayer.playTag = TAG
//            holder.dataBinding.detailPlayer.playPosition = position
//            //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
//            holder.dataBinding.detailPlayer.isAutoFullWithSize = true
//            //音频焦点冲突时是否释放
//            holder.dataBinding.detailPlayer.isReleaseWhenLossAudio = false
//            //全屏动画
//            holder.dataBinding.detailPlayer.isShowFullAnimation = true
//            //小屏时不触摸滑动
//            holder.dataBinding.detailPlayer.setIsTouchWiget(false)
//        }
//    }
//}