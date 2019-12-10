package com.emedinaa.core.ui.events;

import android.view.View;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/4/18
 */
public interface RecyclerClickListener {

    void onClick(View view ,int position);
    void onLongClick(View view , int position);
}
