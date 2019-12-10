package com.emedinaa.chefapp.data.socket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.emedinaa.chefapp.BuildConfig;
import java.net.URISyntaxException;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/18/18
 */
public class SocketManager {
    private Socket mSocket;

    public void init(){
        IO.Options opts = new IO.Options();
        try {
            mSocket = IO.socket(BuildConfig.DOMAIN);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void on(@NonNull String event, @NonNull Emitter.Listener listener){
        mSocket.on(event,listener);
    }

    public void emit(@NonNull String event, @Nullable  Object object,
                     @Nullable Ack ack){
        mSocket.emit(event,object,ack);
    }

    public void removeEvent(@NonNull String event){
        mSocket.off(event);
    }

    public void connect(@NonNull Emitter.Listener listener){
        on(Socket.EVENT_CONNECT,listener);
        mSocket.connect();
    }

    public void logIn(@Nullable Object object,
                      @Nullable Ack ack){
        emit(SocketConstant.EMIT_LOGIN,object,ack);
    }

    public boolean isConnected(){
        return mSocket.connected();
    }

    public void clearSession(){
        mSocket.off(SocketConstant.EMIT_LOGIN);
        mSocket.off(SocketConstant.ON_ORDERS);
        mSocket.off(Socket.EVENT_CONNECT);
        mSocket.disconnect();
    }

    public Socket socket() {
        return mSocket;
    }
}
