package com.books.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, url: String) {
        if (url.contains("http")) {
            Glide.with(view.context)
                .load(url)
                .into(view)
        } else {
            Glide.with(view.context)
                .load(
                    view.context.resources.getIdentifier(
                        url,
                        "drawable",
                        view.context.packageName
                    )
                )
                .into(view)
        }
    }

}