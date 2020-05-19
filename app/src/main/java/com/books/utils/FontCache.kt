package com.books.utils

import android.content.Context
import android.graphics.Typeface


object FontCache {
    private val fontCache: HashMap<String, Typeface?> = HashMap()
    fun getTypeface(fontName: String, context: Context): Typeface? {
        var typeface = fontCache[fontName]
        if (typeface == null) {
            typeface = try {
                Typeface.createFromAsset(context.assets, "fonts/$fontName")
            } catch (e: Exception) {
                return null
            }
            fontCache[fontName] = typeface
        }
        return typeface
    }
}