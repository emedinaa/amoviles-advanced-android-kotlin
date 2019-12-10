package com.emedinaa.chefapp.data.socket;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : Eduardo Medina
 * @see : https://guides.codepath.com/android/Replacing-Enums-with-Enumerated-Annotations
 * @since : 8/17/18
 */
public class SocketConstant {

    // ... type definitions
    // Describes when the annotation will be discarded
    @Retention(RetentionPolicy.SOURCE)
    // Enumerate valid values for this interface
    @StringDef({EMIT_LOGIN, ON_ORDERS})
    // Create an interface for validating String types
    public @interface SocketEventDef {}

    // Declare the constants
    public static final String EMIT_LOGIN = "loginUsuario";
    public static final String ON_ORDERS = "nuevaOrdenDeCompra";

    private String event;
    // Mark the argument as restricted to these enumerated types
    public SocketConstant(@SocketEventDef String event) {
        this.event = event;
    }
}
