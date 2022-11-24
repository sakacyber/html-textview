package com.saka.android.htmltextview.utility

import java.util.regex.Matcher
import java.util.regex.Pattern

object VideoLoader {
    
    fun isYoutubeFormat(it: String): Boolean {
        return it.contains("youtube.com/") || it.contains("youtu.be/")
    }
    
    fun getYouTubeId(youTubeUrl: String?): String? {
        val pattern =
            "https?://(?:[0-9A-Z-]+\\.)?(?:youtu\\.be/|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})(?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|</a>))[?=&+%\\w]*"
        val compiledPattern: Pattern = Pattern.compile(
            pattern,
            Pattern.CASE_INSENSITIVE
        )
        val matcher: Matcher = compiledPattern.matcher(youTubeUrl)
        return if (matcher.find()) {
            matcher.group(1)
        } else null
    }
    
    fun generateIFrameEncode(link: String): String {
        val htmlStart = """
                     <HTML><HEAD>
                     <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">
                     <style>
                     iframe{
                      width : 100% !important;
                      height : 100% !important;
                     }
                     *{
                     padding:0 ;
                     margin:0 ;
                     }
                     </style>
                     </HEAD>
                     <BODY>
                     """
        val htmlEnd = "</BODY></HTML>"
        val content = "<iframe src=\"$link\" style=\"border:none;\"></iframe>"
        return "$htmlStart$content$htmlEnd"
    }
}
