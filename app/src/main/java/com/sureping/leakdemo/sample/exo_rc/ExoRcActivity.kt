package com.sureping.leakdemo.sample.exo_rc

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.sureping.leakdemo.R
import com.sureping.leakdemo.base.BaseActivity
import com.sureping.leakdemo.base.recyclerview.RecyclerBaseAdapter
import com.sureping.leakdemo.base.recyclerview.RecyclerInflate
import kotlinx.android.synthetic.main.activity_main.*

/**
 * author pisa
 * date  2019/7/19
 * version 1.0
 * effect :
 */
class ExoRcActivity : BaseActivity() {
    private var currentPosition: Int = -1
    val adapter = ExoAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exo_rc)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        val list = ArrayList<ExoEntity>()
        list.add(ExoEntity())
        list.add(ExoEntity())
        list.add(ExoEntity())
        list.add(ExoEntity())
        adapter.setData(list)

        recycler_view.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: androidx.recyclerview.widget.RecyclerView, newState: Int) {
                val manager = recyclerView.layoutManager as androidx.recyclerview.widget.LinearLayoutManager?
                if (newState == androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE) {
                    val firstVisibleItem = manager?.findFirstVisibleItemPosition() ?: -1
                    if (firstVisibleItem != -1 && currentPosition != firstVisibleItem) {
                        val view = manager?.findViewByPosition(firstVisibleItem)
                        view?.findViewById<ViewGroup>(R.id.fl_root)?.let {
                            PlayerManager.INSTANCE.init(view.context)
                            PlayerManager.INSTANCE.bind(it, firstVisibleItem)
                        }
                    }
                    currentPosition = firstVisibleItem
                }
            }

            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {}
        })
    }
}