package com.emedinaa.kotlinapp.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.listeners.MessageListener
import android.widget.TextView
import android.text.TextUtils
import android.util.Log

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ShowMessageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var listener: MessageListener? = null

    private var tviMessage: TextView? = null
    private var resultMessage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_show_message, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MessageListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement MessageListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tviMessage = view?.findViewById<View>(R.id.tviMessage) as TextView
        resultMessage = ""
    }

    fun showMessage(message: String?) {
        resultMessage = resultMessage.plus("\n").plus(message)
        tviMessage?.text = resultMessage
    }

    companion object {
        val TAG:String="CONSOLE"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ShowMessageFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
