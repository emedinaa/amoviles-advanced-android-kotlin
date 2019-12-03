package com.emedinaa.kotlinapp.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.listeners.ColorListener
import kotlinx.android.synthetic.main.fragment_bottom_bar.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BottomBarFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var listener:ColorListener?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_bar, container, false)
    }

    override fun onAttach(context: Context) {
      super.onAttach(context)
      if (context is ColorListener) {
          listener = context
      } else {
          throw RuntimeException("$context must implement ColorListener")
      }
  }


  override fun onDetach() {
      super.onDetach()
      listener = null
  }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnBox0.setOnClickListener {
            listener?.onColorSelected(0)
        }

        btnBox1.setOnClickListener {
            listener?.onColorSelected(1)
        }

        btnBox2.setOnClickListener {
            listener?.onColorSelected(2)
        }

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
