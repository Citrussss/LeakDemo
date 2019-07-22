package com.sureping.leakdemo.sample.workmanager

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.work.*
import com.sureping.leakdemo.base.BaseActivity
import java.util.concurrent.TimeUnit

/**
 * author pisa
 * date  2019/7/22
 * version 1.0
 * effect :
 */
class NetWorkManagerActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        work()
    }

    private fun work() {
        val myConstraints = Constraints.Builder()
            //            .setRequiresDeviceIdle(true)//指定{@link WorkRequest}运行时设备是否为空闲
            //            .setRequiresCharging(true)//指定要运行的{@link WorkRequest}是否应该插入设备
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .setRequiresBatteryNotLow(true)//指定设备电池是否不应低于临界阈值
            .setRequiresCharging(true)//网络状态
            //            .setRequiresDeviceIdle(true)//指定{@link WorkRequest}运行时设备是否为空闲
            //            .setRequiresStorageNotLow(true)//指定设备可用存储是否不应低于临界阈值
            //            .addContentUriTrigger(myUri,false)//指定内容{@link android.net.Uri}时是否应该运行{@link WorkRequest}更新
            .build()
        val uploadWorkRequest =
            //            PeriodicWorkRequestBuilder<UploadWorker>(1, TimeUnit.SECONDS)
            OneTimeWorkRequestBuilder<UploadWorker>()
                .setConstraints(myConstraints)
                .build()
        WorkManager.getInstance(this).enqueue(uploadWorkRequest)
    }
}

class UploadWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        Log.e("演示任务", System.currentTimeMillis().toString())

//        Toast.makeText(applicationContext,"演示任务",LENGTH_SHORT).show()
        return Result.success()
    }
}