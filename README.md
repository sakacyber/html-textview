[![](https://jitpack.io/v/sakacyber/html-textview.svg)](https://jitpack.io/#sakacyber/html-textview)

# HtmlTextView

**HtmlTextView** is an Android library for display html content as view. 
**HtmlTextView** supports dark mode/light mode base on system.

Support tag base on build in [Html.fromHtml (String source,
                int flags)](https://developer.android.com/reference/android/text/Html#fromHtml(java.lang.String,%20int)) in android

## Quick Start
**HtmlTextView** is available on jitpack.

Add dependency:

```
implementation "com.github.sakacyber:html-textview:1.0.15"
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
htmlTextView.setText(html, lifecycleScope)
```
to update text size
```
// size in dp
htmlTextView.updateTextSize(18f)
```