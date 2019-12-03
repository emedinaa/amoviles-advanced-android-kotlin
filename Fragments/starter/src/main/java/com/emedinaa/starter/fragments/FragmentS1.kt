package com.emedinaa.starter.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.emedinaa.starter.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentS1 : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var frameLayout:View?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v= inflater.inflate(R.layout.fragment_fragment_s1, container, false)
        frameLayout= v.findViewById(R.id.frameLayout)
        return  v
    }

    fun changeColor(color:String?){
        color?.let {
            frameLayout?.setBackgroundColor(Color.parseColor(it))
        }
    }

    fun showMessage(message: String) {
        //
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FragmentS1().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
