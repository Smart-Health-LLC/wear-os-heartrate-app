package com.progneo.smarthealth.data.repository

import com.progneo.smarthealth.data.api.service.HeartRateService
import com.progneo.smarthealth.domain.model.HeartRateRecord
import com.progneo.smarthealth.domain.repository.HeartRateRemoteRepository
import javax.inject.Inject

class HeartRateRemoteRepositoryImpl @Inject constructor(
    private val service: HeartRateService
) : HeartRateRemoteRepository {

    override suspend fun sendHeartRateRecordList(list: List<HeartRateRecord>): Boolean {
        return service.sendHeartRateRecordList(list).isSuccessful
    }
}
