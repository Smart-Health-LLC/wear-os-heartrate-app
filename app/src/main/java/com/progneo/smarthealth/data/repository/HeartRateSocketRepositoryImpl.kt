package com.progneo.smarthealth.data.repository

import com.progneo.smarthealth.data.api.mapper.toData
import com.progneo.smarthealth.data.api.service.HeartRateSocketService
import com.progneo.smarthealth.domain.model.HeartRateRecord
import com.progneo.smarthealth.domain.repository.HeartRateRemoteRepository
import javax.inject.Inject

class HeartRateSocketRepositoryImpl @Inject constructor(
    private val service: HeartRateSocketService
) : HeartRateRemoteRepository {

    override suspend fun sendHeartRateRecord(record: HeartRateRecord): Boolean {
        return service.sendHeartRateRecord(record.toData())
    }

    override suspend fun sendHeartRateRecordList(list: List<HeartRateRecord>): Boolean {
        return service.sendHeartRateRecordList(list.map { it.toData() })
    }
}
