package com.sureping.leakdemo.sample.exo_rc

import android.databinding.ViewDataBinding
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.sureping.leakdemo.R
import com.sureping.leakdemo.base.recyclerview.RecyclerBaseAdapter
import com.sureping.leakdemo.base.recyclerview.RecyclerBaseHolder
import kotlinx.android.synthetic.main.activity_exo.*
import android.media.MediaMetadataRetriever
import android.provider.Contacts
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import wseemann.media.FFmpegMediaMetadataRetriever
import kotlin.coroutines.experimental.migration.toExperimentalCoroutineContext


/**
 * author pisa
 * date  2019/7/19
 * version 1.0
 * effect :
 */
class ExoAdapter : RecyclerBaseAdapter<ExoEntity>() {

    override fun onBindViewHolder(holder: RecyclerBaseHolder<out ViewDataBinding>, position: Int) {
        super.onBindViewHolder(holder, position)
        val entity = getData()[position]

        entity.dataBinding?.root?.let {
            it.findViewById<ImageView>(R.id.iv_video_pre)
                .let {
                    val url = if (position % 2 == 0) {
                        it.context.getString(R.string.media_url_mp4)
                    } else {
                        it.context.getString(R.string.media_url_mp4_2)
                    }
                    val launch = GlobalScope.launch {
                        val result = async {
                            val retriever = FFmpegMediaMetadataRetriever()
                            retriever.setDataSource(url, HashMap())
                            retriever.frameAtTime
                        }
                        val bitmap = result.await()
                        it.post { it.setImageBitmap(bitmap)}
                    }
                }
        }
//        val entity = getData()[position]
//        entity.dataBinding?.root?.let {
//            PlayerManager.INSTANCE.init(it.context)
//            PlayerManager.INSTANCE.bind(it as ViewGroup)
//        }
    }
}