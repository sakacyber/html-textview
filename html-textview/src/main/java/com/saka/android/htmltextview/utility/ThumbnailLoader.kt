package com.saka.android.htmltextview.utility

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.InsetDrawable
import android.media.MediaMetadataRetriever
import android.os.AsyncTask
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.saka.android.htmltextview.R
import java.util.*

/**
 * class to load video thumbnail in background thread
 */
class ThumbnailLoader(
    private val imageView: ImageView
) : AsyncTask<String, Unit, Bitmap>() {

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        imageView.background = BitmapDrawable(Resources.getSystem(), result)
        imageView.setImageDrawable(
            InsetDrawable(
                ContextCompat.getDrawable(
                    imageView.context,
                    R.drawable.ic_play_circle_24
                ), 80
            )
        )
    }

    override fun doInBackground(vararg params: String?): Bitmap? {
        val bitmap: Bitmap?
        var mediaMetadataRetriever: MediaMetadataRetriever? = null
        try {
            mediaMetadataRetriever = MediaMetadataRetriever()
            mediaMetadataRetriever.setDataSource(params.getOrNull(0), HashMap())
            bitmap = mediaMetadataRetriever.getFrameAtTime(
                6_000_000,
                MediaMetadataRetriever.OPTION_CLOSEST
            )
        } finally {
            mediaMetadataRetriever?.release()
        }
        return bitmap
    }
}
