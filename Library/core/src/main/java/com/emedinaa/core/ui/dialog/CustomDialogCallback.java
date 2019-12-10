package com.emedinaa.core.ui.dialog;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/4/18
 */
public interface CustomDialogCallback {
    void onAction(Object object);
    void onDialogPositive(Object object);
    void onDialogNegative(Object object);
}
