package com.sakacyber.android.sample

import android.content.Context
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class Content {

    val content: String = ""

    companion object {

        @JvmStatic
        fun deserialize(context: Context): Content? {
            try {
                val jsonString = parseResource(context, R.raw.content)
                return Gson().fromJson(jsonString, Content::class.java)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }

        @Throws(IOException::class)
        fun parseResource(context: Context, resource: Int): String {
            val inputStream: InputStream = context.resources.openRawResource(resource)
            val sb = StringBuilder()
            val br = BufferedReader(InputStreamReader(inputStream))
            var read = br.readLine()
            while (read != null) {
                sb.append(read)
                read = br.readLine()
            }
            return sb.toString()
        }
    }
}
