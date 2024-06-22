package com.saka.android.htmltextview.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Base64
import android.view.WindowManager
import android.webkit.WebView
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.ui.PlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.saka.android.htmltextview.R
import com.saka.android.htmltextview.utility.VideoLoader

class VideoActivity : AppCompatActivity() {

    private var player: ExoPlayer? = null
    private var playerView: PlayerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_video)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        val webView = findViewById<WebView>(R.id.webView)
        val link = intent.getStringExtra("link")
        playerView = findViewById(R.id.playerView)
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

    @OptIn(UnstableApi::class)
    private fun loadExoPlayer(link: String, playerView: PlayerView, toolbar: Toolbar) {
        // create player view
        playerView.setShowBuffering(PlayerView.SHOW_BUFFERING_WHEN_PLAYING)
        playerView.setControllerVisibilityListener(
            PlayerView.ControllerVisibilityListener {
                toolbar.visibility = it
            }
        )

        // create media source
        val dataSourceFactory: MediaSource.Factory = DefaultMediaSourceFactory(this)
        val mediaItem = MediaItem.fromUri(link)
//        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
//            .createMediaSource(mediaItem)

        // create player
        player = ExoPlayer.Builder(this)
            .setMediaSourceFactory(dataSourceFactory)
            .build()
        player?.setMediaItem(mediaItem)
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
