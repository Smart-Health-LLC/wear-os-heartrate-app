package com.progneo.smarthealth.data.datasource

import com.progneo.smarthealth.data.entity.HeartRate

interface HeartRateCacheDatasource {
    suspend fun addRecord(heartRate: HeartRate)
    suspend fun getAllRecords(): List<HeartRate>
    suspend fun deleteAllRecords()
}
