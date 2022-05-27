[![](https://jitpack.io/v/sakacyber/html-textview.svg)](https://jitpack.io/#sakacyber/html-textview)

# HtmlTextView

**HtmlTextView** is an Android library for display html content as view. 
**HtmlTextView** supports dark mode/light mode base on system.

Support tag base on build in [Html.fromHtml (String source,
                int flags)](https://developer.android.com/reference/android/text/Html#fromHtml(java.lang.String,%20int)) in android

It currently supports tags </>:

* ```<iframe>```
* ```<a href="...">```
* ```<b>```
* ```<big>```
* ```<blockquote>```
* ```<br>```
* ```<cite>```
* ```<dfn>```
* ```<div align="...">```
* ```<em>```
* ```<font size="..." color="..." face="...">```
* ```<i>```
* ```<img src="...">```
* ```<p>```
* ```<small>```
* ```<strike>```
* ```<strong>```
* ```<sub>```
* ```<sup>```
* ```<tt>```
* ```<u>```

## Quick Start
**HtmlTextView** is availble on jitpack.

Add dependency:

```
implementation "com.github.SakaGamer:html-textview:1.0.13"
```

## Usage
to use **HtmlTextView**:

```
// xml
<com.saka.android.htmltextview.HtmlTextView
    android:id="@+id/htmlTextView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp" />

// activity or fragment
htmlTextView.setText(html)
```
