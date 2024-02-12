package com.progneo.smarthealth.data.api.service

import com.progneo.smarthealth.domain.model.HeartRateRecord
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface HeartRateService {
    @POST("") // TODO: paste path
    suspend fun sendHeartRateRecordList(
        @Body list: List<HeartRateRecord>
    ): Response<Unit>
}
