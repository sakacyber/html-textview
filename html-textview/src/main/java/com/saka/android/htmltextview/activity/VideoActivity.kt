package com.saka.android.htmltextview.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Base64
import android.view.WindowManager
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.saka.android.htmltextview.R
import com.saka.android.htmltextview.utility.VideoLoader

class VideoActivity : AppCompatActivity() {

    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_video)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        val playerView = findViewById<StyledPlayerView>(R.id.playerView)
        val webView = findViewById<WebView>(R.id.webView)
        val link = intent.getStringExtra("link")
        link?.let {
            when {
                VideoLoader.isYoutubeFormat(it) -> {
                    youTubePlayerView.isVisible = true
                    loadYoutubePlayer(VideoLoader.getYouTubeId(link), youTubePlayerView, toolbar)
                }
                else -> {
                    webView.isVisible = true
                    loadIFrameWebView(it, webView)
                }
            }
        }
    }

    private fun loadExoPlayer(link: String, playerView: StyledPlayerView, toolbar: Toolbar) {
        // create player view
        playerView.setShowBuffering(StyledPlayerView.SHOW_BUFFERING_WHEN_PLAYING)
        playerView.setControllerVisibilityListener(
            StyledPlayerView.ControllerVisibilityListener {
                toolbar.visibility = it
            }
        )

        // create media source
        val dataSourceFactory = DefaultDataSource.Factory(this)
        val mediaItem = MediaItem.fromUri(link)
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(mediaItem)

        // create player
        player = ExoPlayer.Builder(this).build()
        player?.setMediaSource(mediaSource)
        player?.prepare()
        player?.playWhenReady = true
        playerView.player = player
    }

    private fun loadYoutubePlayer(
        videoId: String?,
        playerView: YouTubePlayerView,
        toolbar: Toolbar
    ) {
        if (videoId == null) return
        lifecycle.addObserver(playerView)
        playerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                toolbar.isVisible = true
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadIFrameWebView(link: String, webView: WebView) {
        webView.settings.javaScriptEnabled = true
        val encodedHtml = Base64.encodeToString(
            VideoLoader.generateIFrameEncode(link).toByteArray(),
            Base64.NO_PADDING
        )
        webView.loadData(encodedHtml, "text/html", "base64")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onPause() {
        super.onPause()
        if (player != null) {
            player?.playWhenReady = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (player != null) {
            player?.stop()
            player?.release()
            player = null
        }
    }
}
