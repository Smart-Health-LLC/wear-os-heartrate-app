package com.progneo.smarthealth.data.api.service

import com.google.gson.Gson
import com.progneo.smarthealth.data.api.model.CaptureRequest
import com.progneo.smarthealth.data.api.socket.WebSocketManager
import javax.inject.Inject

class HeartRateSocketService @Inject constructor(
    private val webSocketManager: WebSocketManager
) {

    fun sendHeartRateRecord(capture: CaptureRequest): Boolean {
        val isSuccess = webSocketManager.sendMessage(Gson().toJson(capture))
        if (!isSuccess) {
            webSocketManager.connect()
        }
        return isSuccess
    }

    fun sendHeartRateRecordList(list: List<CaptureRequest>): Boolean {
        val isSuccess = webSocketManager.sendMessage(Gson().toJson(list))
        if (!isSuccess) {
            webSocketManager.connect()
        }
        return isSuccess
    }
}
