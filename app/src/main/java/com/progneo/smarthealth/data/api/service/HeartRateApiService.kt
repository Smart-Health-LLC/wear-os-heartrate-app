package com.progneo.smarthealth.data.api.service

import com.progneo.smarthealth.data.api.model.CaptureRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface HeartRateApiService {

    @POST("captures")
    suspend fun sendHeartRateRecordList(
        @Body list: List<CaptureRequest>
    ): Response<Unit>
}
