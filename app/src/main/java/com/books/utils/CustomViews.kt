package com.books.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.books.R
import com.books.utils.FontCache.getTypeface

private const val ANDROIDSCHEMA = "http://schemas.android.com/apk/res/android"

class CustomTextView : AppCompatTextView {

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        applyCustomFont(context, attrs!!)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        applyCustomFont(context, attrs!!)
    }

    private fun applyCustomFont(
        context: Context,
        attrs: AttributeSet
    ) {
        val attributeArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomTextView
        )
        val fontName = attributeArray.getString(R.styleable.CustomTextView_fontText)
        val textStyle =
            attrs.getAttributeIntValue(ANDROIDSCHEMA, "textStyle", Typeface.BOLD)
        val customFont = selectTypeface(context, fontName, textStyle)
        typeface = customFont
        attributeArray.recycle()
    }
}

class CustomButton : AppCompatButton {
    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        applyCustomFont(context, attrs!!)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        applyCustomFont(context, attrs!!)
    }

    private fun applyCustomFont(
        context: Context,
        attrs: AttributeSet
    ) {
        val attributeArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomButton
        )
        val fontName = attributeArray.getString(R.styleable.CustomButton_fontButton)
        val textStyle =
            attrs.getAttributeIntValue(ANDROIDSCHEMA, "textStyle", Typeface.BOLD)

        val customFont = selectTypeface(context, fontName, textStyle)
        typeface = customFont
        attributeArray.recycle()
    }
}

private fun selectTypeface(
    context: Context,
    fontName: String?,
    textStyle: Int
): Typeface? {
    return getTypeface("$fontName", context)
}