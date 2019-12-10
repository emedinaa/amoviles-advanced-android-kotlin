package com.emedinaa.core.helpers;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/17/18
 */
public class StringHelper {

    public SpannableStringBuilder spannableStringBuilder(String text) {
        RelativeSizeSpan smallSizeText = new RelativeSizeSpan(.7f);
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(text);
        ssBuilder.setSpan(
                smallSizeText,
                text.indexOf("."),
                text.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        return ssBuilder;
    }

    /*
        SpannableString price= new SpannableString("S/."+dish.getPrice());
        price.setSpan(new SuperscriptSpan(),0,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        price.setSpan(new RelativeSizeSpan(0.5f), 0,3, 0); // set size
     */
}
