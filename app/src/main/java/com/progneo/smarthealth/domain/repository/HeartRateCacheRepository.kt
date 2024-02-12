package com.progneo.smarthealth.domain.repository

import com.progneo.smarthealth.domain.model.HeartRateRecord

interface HeartRateCacheRepository {
    suspend fun addRecord(heartRateRecord: HeartRateRecord)
    suspend fun getAllRecords(): List<HeartRateRecord>
    suspend fun deleteAllRecords()
}
