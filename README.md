[![](https://jitpack.io/v/sakacyber/html-textview.svg)](https://jitpack.io/#sakacyber/html-textview)

# HtmlTextView

**HtmlTextView** Android project is an open-source library that enables developers to display formatted HTML content in a TextView widget within an Android application.

With this library, developers can easily display HTML content, including text, images, links, and other media, using a TextView widget without the need for any additional customization or coding. This is particularly useful when developers want to display formatted content that has been obtained from a web API or other online source, or when they want to create rich-text documents within their application.

Support tag base on build in [Html.fromHtml (String source,
 int flags)](https://developer.android.com/reference/android/text/Html#fromHtml(java.lang.String,%20int)) in android

## Quick Start

**HtmlTextView** is available on jitpack.

Add dependency:

```groovy
implementation "com.github.sakacyber:html-textview:1.0.15"
```

## Usage

to use **HtmlTextView**:

```xml
<com.saka.android.htmltextview.HtmlTextView
    android:id="@+id/htmlTextView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp" />
```

In Activity or Fragment

```kotlin
// activity or fragment
htmlTextView.setText(html, lifecycleScope)
```

update text size

```kotlin
// size in dp
htmlTextView.updateTextSize(18f)
```