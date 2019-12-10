package com.emedinaa.core.media;

import android.widget.ImageView;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/11/18
 */
public interface ImageLoader {

    void load(String url, ImageView imageView);
    void loadCircle(String url, ImageView imageView);
    void loadLocal(String path, ImageView imageView);
}
