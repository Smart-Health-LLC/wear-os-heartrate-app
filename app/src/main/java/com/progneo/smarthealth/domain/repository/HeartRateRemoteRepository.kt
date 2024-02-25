package com.progneo.smarthealth.domain.repository

import com.progneo.smarthealth.domain.model.HeartRateRecord

interface HeartRateRemoteRepository {

    suspend fun sendHeartRateRecord(record: HeartRateRecord): Boolean
    suspend fun sendHeartRateRecordList(list: List<HeartRateRecord>): Boolean
}
