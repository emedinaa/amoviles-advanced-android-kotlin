package com.emedinaa.kotlinapp.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import java.io.Serializable;

/**
 * Created by eduardomedina on 1/02/17.
 */
public class DisplayUtils {

    public static float getCurrentDIP(Context context)
    {
        float d = context.getResources().getDisplayMetrics().density;
        return d;
    }

    public static DisplayInfo getCurrentInfo(Context context)
    {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        return new DisplayInfo(getDip(getCurrentDIP(context),d.widthPixels),
                getDip(getCurrentDIP(context),d.heightPixels));
    }

    public static  int getDip(float scale, int pixel)
    {
        return (int) (pixel * scale + 0.5f);
    }

    public  static  class DisplayInfo implements Serializable
    {
        private int width;
        private int height;


        public DisplayInfo(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
