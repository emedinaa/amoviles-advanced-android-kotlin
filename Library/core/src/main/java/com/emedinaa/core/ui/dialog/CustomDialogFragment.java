package com.emedinaa.core.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.emedinaa.core.R;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/4/18
 */
public class CustomDialogFragment extends DialogFragment {
    private static final String TAG = "CustomDialogF";
    private CustomDialogCallback mListener;

    public CustomDialogFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CustomDialogCallback) {
            mListener = (CustomDialogCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CustomDialogListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View customView= inflater.inflate(R.layout.fragment_custom_dialog,null);
        builder.setView(customView)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositive(null);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CustomDialogFragment.this.getDialog().cancel();
                        if(mListener!=null){
                            mListener.onDialogNegative(null);
                        }
                    }
                });
        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }
}
