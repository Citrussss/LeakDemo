package com.sureping.leakdemo.sample.exo

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.sureping.leakdemo.base.BaseActivity
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import kotlinx.android.synthetic.main.activity_exo.*
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.sureping.leakdemo.R


/**
 * author pisa
 * date  2019/7/19
 * version 1.0
 * effect :
 */
class ExoPlayerActivity : BaseActivity() {
    private var playWhenReady: Boolean = true
    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0L
    private var player: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exo)
        initializePlayer()
        initBar()
    }

    private fun initBar() {
        tv_bottom_bar.setOnClickListener {
            it.visibility = View.GONE
            video_view.showController()
        }
    }

    private fun initializePlayer() {
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(
                this,
                DefaultRenderersFactory(this),
                DefaultTrackSelector(), DefaultLoadControl()
            )
            video_view.player = player
            video_view.setControllerVisibilityListener {
                tv_bottom_bar.visibility =
                    if (it == View.VISIBLE) View.GONE
                    else View.VISIBLE
            }
//            video_view.showController()
            player?.playWhenReady = playWhenReady
            player?.seekTo(currentWindow, playbackPosition)
        }

        val uri = Uri.parse(getString(R.string.media_url_mp4))
        val mediaSource = buildMediaSource(uri)
        player?.prepare(mediaSource, false, true)
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        return ExtractorMediaSource.Factory(
            DefaultHttpDataSourceFactory("exoplayer-codelab")
        ).createMediaSource(uri)
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    private fun releasePlayer() {
        if (player != null) {
            val exoPlayer: SimpleExoPlayer = player!!
            playbackPosition = exoPlayer.currentPosition
            currentWindow = exoPlayer.currentWindowIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
            player = null
        }
    }
}