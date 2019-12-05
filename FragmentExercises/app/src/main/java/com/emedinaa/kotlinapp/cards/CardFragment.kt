package com.emedinaa.kotlinapp.cards

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.graphics.Matrix
import android.util.DisplayMetrics
import java.util.*
import com.emedinaa.kotlinapp.R
import android.view.MotionEvent
import android.util.Log
import com.emedinaa.kotlinapp.utils.DisplayUtils


class CardFragment: Fragment() {

    private  var listener:OnCardListener?=null

    private val matrix = Matrix()
    private var xCoord: Int = 0
    private var yCoord: Int = 0
    private var center: Int = 0
    private var angle: Float = 0.0f
    private var startAngle: Int = 0

    private var windowWidth: Int = 0
    private var windowHeight: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        calculateDisplayDimentions()
        center = windowWidth/2
        startAngle= randombyRange(-5,5)

        view?.rotation=startAngle.toFloat()

        view?.setOnTouchListener(View.OnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                }

                MotionEvent.ACTION_MOVE -> {
                    xCoord = motionEvent.rawX.toInt()
                    yCoord = motionEvent.rawY.toInt()

                    view?.x = (xCoord - center + 40).toFloat()
                    view?.y = (yCoord - windowHeight / 2).toFloat()

                    angle = if (xCoord >= center) {
                        ((xCoord - center) * (Math.PI / 32)).toFloat()

                    } else {
                        ((xCoord - center) * (Math.PI / 32)).toFloat()
                    }
                    view?.rotation = angle
                    //Log.v("CONSOLE", "angle $angle")
                }

                MotionEvent.ACTION_UP -> {

                    if (Math.abs(angle) > 40) {
                        listener?.removeCard(this@CardFragment)
                        return@OnTouchListener true
                    }
                    xCoord = motionEvent.rawX.toInt()
                    yCoord = motionEvent.rawY.toInt()

                    view?.x = 20 * DisplayUtils.getCurrentDIP(activity)
                    view?.y = 10 * DisplayUtils.getCurrentDIP(activity)
                    view?.rotation = startAngle.toFloat()
                    angle = 0.0f
                }
            }
            true
        })
    }
    private fun calculateDisplayDimentions() {
        val metrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
        windowWidth = metrics.widthPixels
        windowHeight = metrics.heightPixels
    }

    private fun randombyRange(min: Int, max: Int): Int {
        val range = max - min + 1
        return Random().nextInt(range) + min
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCardListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnCardListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}