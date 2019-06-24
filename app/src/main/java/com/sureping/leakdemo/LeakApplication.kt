package com.sureping.leakdemo

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

class LeakApplication : Application() {

    private lateinit var refWatcher: RefWatcher
    override fun onCreate() {
        super.onCreate()
        application = this
        refWatcher = setupLeakCanary()
    }
    private fun setupLeakCanary(): RefWatcher {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }
    companion object {
        lateinit var application :LeakApplication
        fun getRefWatcher(): RefWatcher {
            return application.refWatcher
        }
    }
}