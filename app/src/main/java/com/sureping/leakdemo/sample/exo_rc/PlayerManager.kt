package com.sureping.leakdemo.sample.exo_rc

import android.content.Context
import android.net.Uri
import android.support.v7.app.AppCompatViewInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.google.android.exoplayer2.upstream.FileDataSourceFactory
import com.sureping.leakdemo.LeakApplication
import com.sureping.leakdemo.R
import java.util.regex.Pattern

/**
 * author pisa
 * date  2019/7/19
 * version 1.0
 * effect :
 */
class PlayerManager {
    companion object {
        val INSTANCE by lazy {
            PlayerManager()
        }
    }

    private var playWhenReady: Boolean = true
    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0L
    private var player: SimpleExoPlayer? = null
    private lateinit var viewPlay: View
    private var position: Int = 0;
    private var init: Boolean = false
    open fun bind(viewGroup: ViewGroup, position: Int) {
        this.position = position
        if (viewPlay.parent != null && viewPlay.parent is ViewGroup) {
            (viewPlay.parent as ViewGroup).removeViewInLayout(viewPlay);
        }
        viewGroup.addView(viewPlay)
        val url = if (position % 2 == 0) {
            viewGroup.context.getString(R.string.media_url_mp4)
        } else {
            viewGroup.context.getString(R.string.media_url_mp4_2)
        }
        play(LeakApplication.getVideoProxy().getProxyUrl(url))
    }

    fun init(context: Context) {
        if (init) {
            return
        } else init = true
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        viewPlay = inflater.inflate(R.layout.item_player, null, false)
        initBar(viewPlay)
        initializePlayer(viewPlay)
    }

    private fun initBar(root: View) {
        root.findViewById<TextView>(
            R.id.tv_bottom_bar
        ).setOnClickListener {
            it.visibility = View.GONE
            root.findViewById<PlayerView>(R.id.video_view).showController()
        }
    }

    private fun initializePlayer(root: View) {
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(
                root.context,
                DefaultRenderersFactory(root.context),
                DefaultTrackSelector(), DefaultLoadControl()
            )
        }

        root.findViewById<PlayerView>(R.id.video_view).player = player
        root.findViewById<PlayerView>(R.id.video_view).setControllerVisibilityListener {
            root.findViewById<TextView>(R.id.tv_bottom_bar).visibility =
                if (it == View.VISIBLE) View.GONE
                else View.VISIBLE
        }
        player?.playWhenReady = playWhenReady
        player?.seekTo(currentWindow, playbackPosition)
    }

    private fun play(url: String) {
        val uri = Uri.parse(url)
        val mediaSource = buildMediaSource(uri)
        player?.prepare(mediaSource, false, true)
    }
    private fun buildMediaSource(uri: Uri): MediaSource {
        if (uri.path.isNetUrl()){
            return ExtractorMediaSource.Factory(
                DefaultHttpDataSourceFactory("exoplayer-codelab")
            ).createMediaSource(uri)
        }else{
            return ExtractorMediaSource.Factory(
                FileDataSourceFactory(null)
            ).createMediaSource(uri)
        }
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
    fun String.isNetUrl():Boolean= Pattern.matches("http[\\s\\S]*",this)
}