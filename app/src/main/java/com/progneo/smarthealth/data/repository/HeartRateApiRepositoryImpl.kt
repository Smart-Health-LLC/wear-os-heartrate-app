package com.progneo.smarthealth.data.repository

import com.progneo.smarthealth.data.api.mapper.toData
import com.progneo.smarthealth.data.api.service.HeartRateApiService
import com.progneo.smarthealth.domain.model.HeartRateRecord
import com.progneo.smarthealth.domain.repository.HeartRateRemoteRepository
import javax.inject.Inject

class HeartRateApiRepositoryImpl @Inject constructor(
    private val service: HeartRateApiService
) : HeartRateRemoteRepository {

    override suspend fun sendHeartRateRecord(record: HeartRateRecord): Boolean {
        return service.sendHeartRateRecordList(listOf(record.toData())).isSuccessful
    }

    override suspend fun sendHeartRateRecordList(list: List<HeartRateRecord>): Boolean {
        val capturesList = list.map { it.toData() }
        return service.sendHeartRateRecordList(capturesList).isSuccessful
    }
}
