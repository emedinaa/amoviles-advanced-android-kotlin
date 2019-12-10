package com.emedinaa.kotlinapp.dish

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import android.widget.TextView
import com.emedinaa.kotlinapp.R
import androidx.appcompat.app.AlertDialog

class NumberPickerDialog:DialogFragment() {

    private var buttonOk: View? = null
    private var buttonPlus: View? = null
    private var buttonMinus: View? = null
    private var textViewNumber: TextView? = null
    private var listener: NumberPickerListener? = null
    private var amount = 0

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        // Get the layout inflater
        val inflater = activity!!.layoutInflater

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        val customView = inflater.inflate(R.layout.layout_number_picker, null)
        buttonOk = customView.findViewById(R.id.buttonOk)
        buttonPlus = customView.findViewById(R.id.buttonPlus)
        buttonMinus = customView.findViewById(R.id.buttonMinus)
        textViewNumber = customView.findViewById(R.id.textViewNumber)

        builder.setView(customView)
        //events
        buttonOk?.setOnClickListener{
            listener?.let {
                it.onDialogOk(amount)
                dismiss()
            }
        }

        buttonPlus?.setOnClickListener{
            amount++
            textViewNumber?.text=amount.toString()
        }

        buttonMinus?.setOnClickListener {
            amount--
            if (amount < 0) {
                amount = 0
            }
            textViewNumber?.text=amount.toString()
        }

        return builder.create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NumberPickerListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement NumberPickerListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun updateTextView() {
        textViewNumber?.text=amount.toString()
    }

    interface NumberPickerListener {
        fun onDialogOk(amount: Int)
        fun onDialogCancel(obj: Any)
    }

}