package com.karim.currencyconverter.binding

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingUtils {
    @JvmStatic
    @BindingAdapter("resourceImage")
    fun bindLoadImage(view: ImageView, uri: String) {
        Glide.with(view.context)
            .load(Uri.parse(uri))
            .into(view)
    }
}