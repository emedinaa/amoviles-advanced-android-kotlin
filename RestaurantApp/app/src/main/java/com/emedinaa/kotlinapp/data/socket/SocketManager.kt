package com.emedinaa.kotlinapp.data.socket

import io.socket.client.Ack
import io.socket.emitter.Emitter
import androidx.annotation.Nullable
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class SocketManager {
    private var mSocket: Socket? = null

    init {
        //val opts = IO.Options()
        try {
            mSocket = IO.socket("https://diplomado-restaurant-backend.herokuapp.com/")
        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }
    }

    private fun on(event: String, listener: Emitter.Listener) {
        mSocket?.on(event, listener)
    }

    private fun emit(event: String, @Nullable obj: Any, @Nullable ack: Ack) {
        mSocket?.emit(event, obj, ack)
    }

    fun removeEvent(event: String) {
        mSocket?.off(event)
    }

    fun connect(listener: Emitter.Listener) {
        on(Socket.EVENT_CONNECT, listener)
        mSocket?.connect()
    }

    fun logIn(@Nullable obj: Any, @Nullable ack: Ack) {
        emit(SocketConstant.EMIT_LOGIN, obj, ack)
    }

    fun isConnected(): Boolean {
        return mSocket?.connected()?:false
    }

    fun clearSession() {
        mSocket?.off(SocketConstant.EMIT_LOGIN)
        mSocket?.off(SocketConstant.EMIT_ORDER)
        mSocket?.off(Socket.EVENT_CONNECT)
        mSocket?.disconnect()
    }

    fun socket(): Socket? {
        return mSocket
    }
}