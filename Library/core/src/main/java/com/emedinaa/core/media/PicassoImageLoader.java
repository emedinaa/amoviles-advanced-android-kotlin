package com.emedinaa.core.media;

import android.widget.ImageView;

import com.emedinaa.core.media.transform.CircleTransform;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * @author : Eduardo Medina
 * @see : https://github.com/emedinaa/android-without-libraries/tree/dev/MeetupApp/MeetupApp/app/src/main/java/com/emedinaa/meetupapp/common/media
 * @since : 8/11/18
 */
public class PicassoImageLoader implements ImageLoader{

    @Override
    public void load(String url, ImageView imageView) {
        Picasso.get()
                .load(url)
                .into(imageView);
    }

    @Override
    public void loadCircle(String url, ImageView imageView) {
        Picasso.get()
                .load(url)
                .transform(new CircleTransform())
                .into(imageView);
    }

    @Override
    public void loadLocal(String path, ImageView imageView) {
        Picasso.get()
                .load(new File(path))
                .into(imageView);
    }
}
