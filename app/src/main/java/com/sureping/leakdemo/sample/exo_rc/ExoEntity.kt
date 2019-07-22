package com.sureping.leakdemo.sample.exo_rc

import com.sureping.leakdemo.R
import com.sureping.leakdemo.base.recyclerview.RecyclerInflate
import com.sureping.leakdemo.databinding.HolderExoBinding

/**
 * author pisa
 * date  2019/7/19
 * version 1.0
 * effect :
 */
class ExoEntity() : RecyclerInflate<HolderExoBinding>() {
    override fun getLayoutId(): Int {
       return R.layout.holder_exo
    }
}