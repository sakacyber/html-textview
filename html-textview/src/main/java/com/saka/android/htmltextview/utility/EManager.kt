package com.saka.android.htmltextview.utility

import android.content.Context
import android.util.DisplayMetrics
import android.view.ViewGroup
import com.saka.android.htmltextview.element.BaseElement
import com.saka.android.htmltextview.element.BlockQuoteView
import com.saka.android.htmltextview.element.DivView
import com.saka.android.htmltextview.element.HeaderView
import com.saka.android.htmltextview.element.IFrameView
import com.saka.android.htmltextview.element.ImageView
import com.saka.android.htmltextview.element.ParagraphView
import com.saka.android.htmltextview.element.WebView
import kotlinx.coroutines.CoroutineScope
import org.jsoup.Jsoup
import org.jsoup.select.Elements

object EManager {
    
    fun appendView(
        view: ViewGroup,
        elements: Elements,
        content: String = "",
        coroutineScope: CoroutineScope?,
    ) {
        var child: BaseElement
        for (e in elements) {
            when (e.tagName()) {
                "blockquote" -> {
                    child = BlockQuoteView(
                        context = view.context,
                        element = e,
                        content = content,
                        coroutineScope = coroutineScope,
                    )
                }
                "h" -> {
                    child = HeaderView(
                        context = view.context,
                        element = e,
                        content = content,
                        coroutineScope = coroutineScope,
                    )
                }
                "iframe" -> {
                    child = IFrameView(
                        context = view.context,
                        element = e,
                        content = content,
                        coroutineScope = coroutineScope,
                    )
                }
                "p" -> {
                    child = ParagraphView(
                        context = view.context,
                        element = e,
                        content = content,
                        coroutineScope = coroutineScope,
                    )
                }
                "img" -> {
                    child = ImageView(
                        context = view.context,
                        element = e,
                        content = content,
                        coroutineScope = coroutineScope,
                    )
                }
                "div" -> {
                    child = DivView(
                        context = view.context,
                        element = e,
                        content = content,
                        coroutineScope = coroutineScope,
                    )
                }
                "table" -> {
                    child = WebView(
                        context = view.context,
                        element = e,
                        content = content,
                        coroutineScope = coroutineScope,
                    )
                }
                "ul" -> {
                    child = WebView(
                        context = view.context,
                        element = e,
                        content = content,
                        coroutineScope = coroutineScope,
                    )
                }
                "ol" -> {
                    child = WebView(
                        context = view.context,
                        element = e,
                        content = content,
                        coroutineScope = coroutineScope,
                    )
                }
                else -> {
                    child = HeaderView(
                        context = view.context,
                        element = e,
                        content = content,
                        coroutineScope = coroutineScope,
                    )
                }
            }
            view.addView(child)
        }
    }
    
    fun dpToPx(context: Context, dp: Int): Float {
        val displayMetrics = context.resources.displayMetrics
        return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }
    
    fun findAllVideoLinks(content: String?): List<String> {
        content ?: return emptyList()
        val links: MutableList<String> = ArrayList()
        val document = Jsoup.parse(content)
        val medias = document.select("[src]")
        for (element in medias) {
            if (element.tagName() == "iframe") {
                links.add(element.attr("abs:src"))
            }
        }
        return links
    }
    
    fun findAllImagesLinks(content: String?): List<String> {
        content ?: return emptyList()
        val links: MutableList<String> = ArrayList()
        val document = Jsoup.parse(content)
        val medias = document.select("[src]")
        for (element in medias) {
            if (element.tagName() == "img") {
                links.add(element.attr("abs:src"))
            }
        }
        return links
    }
}
