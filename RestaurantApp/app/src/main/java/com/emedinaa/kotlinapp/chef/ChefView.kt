package com.emedinaa.kotlinapp.chef
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.emedinaa.kotlinapp.R

class ChefView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        addView(View.inflate(context, R.layout.layout_chef_view,null))
    }
}