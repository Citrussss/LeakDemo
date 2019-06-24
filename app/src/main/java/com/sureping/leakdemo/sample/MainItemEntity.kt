package com.sureping.leakdemo.sample

import android.app.Activity
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import com.sureping.leakdemo.R
import com.sureping.leakdemo.base.BaseActivity
import com.sureping.leakdemo.base.recyclerview.RecyclerInflate

class MainItemEntity(position: String,val clazz: Class<out Activity>) : RecyclerInflate() {
     var title : String = "例子:$position"
    override fun getLayoutId(): Int = R.layout.holder_test
    fun onRouteClick(view: View?){
        val intent: Intent = Intent(view?.context,clazz)
        view?.context?.let { startActivity(it,intent,null) }
    }
}