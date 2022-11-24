package com.saka.android.htmltextview.utility

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

suspend fun String?.loadBitmap(
    onPostExecute: (Bitmap?) -> Unit,
) {
    val url = this ?: return
    var bitmap: Bitmap? = null
    withContext(Dispatchers.IO) {
        if (VideoLoader.isYoutubeFormat(url)) {
            try {
                val link =
                    URL("http://img.youtube.com/vi/${VideoLoader.getYouTubeId(url)}/0.jpg")
                bitmap = BitmapFactory.decodeStream(link.openConnection().getInputStream())
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        } else {
            var mediaMetadataRetriever: MediaMetadataRetriever? = null
            try {
                mediaMetadataRetriever = MediaMetadataRetriever()
                mediaMetadataRetriever.setDataSource(url, HashMap())
                bitmap = mediaMetadataRetriever.getFrameAtTime(
                    6_000_000,
                    MediaMetadataRetriever.OPTION_CLOSEST
                )
            } catch (ex: Exception) {
                ex.printStackTrace()
            } finally {
                mediaMetadataRetriever?.release()
            }
        }
    }
    onPostExecute.invoke(bitmap)
}

fun <R> CoroutineScope.executeAsyncTask(
    onPreExecute: () -> Unit,
    doInBackground: () -> R,
    onPostExecute: (R) -> Unit,
) = launch {
    onPreExecute()
    val result =
        withContext(Dispatchers.IO) { // runs in background thread without blocking the Main Thread
            doInBackground()
        }
    onPostExecute(result)
}