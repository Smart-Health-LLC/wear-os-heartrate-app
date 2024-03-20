package com.progneo.smarthealth.data.api.socket

import android.util.Log
import java.net.URI
import org.java_websocket.client.WebSocketClient
import org.java_websocket.exceptions.WebsocketNotConnectedException
import org.java_websocket.handshake.ServerHandshake

interface WebSocketManager {

    fun connect()
    fun sendMessage(message: String): Boolean
    fun isConnected(): Boolean
}

class WebSocketManagerImpl(serverUri: URI) : WebSocketClient(serverUri), WebSocketManager {

    init {
        connect()
    }

    override fun onOpen(handshakeData: ServerHandshake?) {
        Log.i(javaClass.simpleName, "Connected: $handshakeData")
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        Log.i(javaClass.simpleName, "Closed with code $code. Reason: $reason")
    }

    override fun onMessage(message: String?) {
        Log.i(javaClass.simpleName, "Received: $message")
    }

    override fun onError(e: Exception?) {
        Log.e(javaClass.simpleName, e?.stackTraceToString() ?: "Unhandled exception")
    }

    override fun connect() {
        super.connect()
    }

    override fun sendMessage(message: String): Boolean {
        return try {
            send(message)
            true
        } catch (ex: WebsocketNotConnectedException) {
            false
        }
    }

    override fun isConnected(): Boolean {
        return connection != null
    }
}
