package com.progneo.smarthealth.dao.source

import com.progneo.smarthealth.dao.db.HeartRateDao
import com.progneo.smarthealth.dao.mapper.HeartRateMapper
import com.progneo.smarthealth.data.datasource.HeartRateCacheDatasource
import com.progneo.smarthealth.data.entity.HeartRate
import javax.inject.Inject

class HeartRateCacheDatasourceImpl @Inject constructor(
    private val heartRateDao: HeartRateDao,
    private val heartRateMapper: HeartRateMapper
) : HeartRateCacheDatasource {

    override suspend fun addRecord(heartRate: HeartRate) {
        heartRateDao.addRecord(heartRateMapper.mapToCached(heartRate))
    }

    override suspend fun getAllRecords(): List<HeartRate> {
        return heartRateDao.getAllRecords()
            .map { cachedHeartRate ->
                heartRateMapper.mapFromCached(cachedHeartRate)
            }
    }

    override suspend fun deleteAllRecords() {
        heartRateDao.deleteAllRecords()
    }
}
