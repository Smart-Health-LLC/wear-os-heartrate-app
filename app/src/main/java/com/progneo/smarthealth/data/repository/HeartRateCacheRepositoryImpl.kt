package com.progneo.smarthealth.data.repository

import com.progneo.smarthealth.data.cache.db.HeartRateDao
import com.progneo.smarthealth.data.cache.mapper.toCache
import com.progneo.smarthealth.data.cache.mapper.toDomain
import com.progneo.smarthealth.domain.model.HeartRateRecord
import com.progneo.smarthealth.domain.repository.HeartRateCacheRepository
import javax.inject.Inject

class HeartRateCacheRepositoryImpl @Inject constructor(
    private val heartRateDao: HeartRateDao
) : HeartRateCacheRepository {

    override suspend fun addRecord(heartRateRecord: HeartRateRecord) {
        heartRateDao.addRecord(heartRateRecord.toCache())
    }

    override suspend fun getAllRecords(): List<HeartRateRecord> {
        return heartRateDao.getAllRecords()
            .map { cachedHeartRate ->
                cachedHeartRate.toDomain()
            }
    }

    override suspend fun deleteAllRecords() {
        heartRateDao.deleteAllRecords()
    }

    override suspend fun getCount(): Int {
        return heartRateDao.getCount()
    }
}
