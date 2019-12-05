package com.emedinaa.kotlinapp


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import kotlinx.android.synthetic.main.fragment_bottom_bar.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * run operators
 * https://kotlinlang.org/docs/reference/scope-functions.html
 */
class BottomBarFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModel: ColorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(ColorViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_bar, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnBox0.setOnClickListener {
            selectedColor(1)
        }

        btnBox1.setOnClickListener {
            selectedColor(2)
        }

        btnBox2.setOnClickListener {
            selectedColor(3)
        }
    }

    private fun selectedColor(color:Int){
        Log.v("CONSOLE", "bottomBarFragment $color")
        viewModel.selectedColor(color)
    }

  companion object {
    @JvmStatic
    fun newInstance(param1: String, param2: String) =
            BottomBarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
  }
}
