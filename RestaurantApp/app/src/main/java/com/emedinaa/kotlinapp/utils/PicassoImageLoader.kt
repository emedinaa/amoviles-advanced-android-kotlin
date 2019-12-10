package com.emedinaa.kotlinapp.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.io.File

class PicassoImageLoader:ImageLoader {

    override fun load(url: String, imageView: ImageView) {
        Picasso.get()
            .load(url)
            .into(imageView)
    }

    override fun loadWithResize(url: String, imageView: ImageView, resize: Int) {
        Picasso.get()
            .load(url)
            .resize(resize,resize)
            .into(imageView)
    }

    override fun loadCircle(url: String, imageView: ImageView) {
        Picasso.get()
            .load(url)
            .transform(CircleTransform())
            .into(imageView)
    }

    override fun loadLocal(path: String, imageView: ImageView) {
        Picasso.get()
            .load(File(path))
            .into(imageView)
    }
}