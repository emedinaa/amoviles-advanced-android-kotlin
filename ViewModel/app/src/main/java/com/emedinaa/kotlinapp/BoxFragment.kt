package com.emedinaa.kotlinapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import kotlinx.android.synthetic.main.fragment_box.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BoxFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class BoxFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        return inflater.inflate(R.layout.fragment_box, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //observers
        viewModel.colorSelected.observe(this,colorSelectedObserver)
    }

    private val colorSelectedObserver= Observer<Int> {
        Log.v("CONSOLE", "BoxFragment colorSelectedObserver $it")
        paintColor(it)
    }

    private fun paintColor(position: Int){
        val color = when(position) {
            1 -> Color.parseColor("#CC2EFA")
            2 -> Color.parseColor("#FE2E2E")
            3 -> Color.parseColor("#F7FE2E")
            else -> 0
        }
        flayBox.setBackgroundColor(color)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                BoxFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
