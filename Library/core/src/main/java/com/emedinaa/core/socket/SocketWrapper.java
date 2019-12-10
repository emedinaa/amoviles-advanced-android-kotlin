package com.emedinaa.core.socket;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/17/18
 */
public interface SocketWrapper {

    //setup
    void init();

    //connexion
    boolean connected();
    void disconnect();

    //emit event
    void on(String event,Object object);
    void emit(String event,Object object);
    void emitOn(String event,Object object);
}
