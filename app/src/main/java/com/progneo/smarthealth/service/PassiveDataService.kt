package com.progneo.smarthealth.service

import androidx.health.services.client.PassiveListenerService
import androidx.health.services.client.data.DataPointContainer
import androidx.health.services.client.data.DataType
import com.progneo.smarthealth.data.repository.PassiveDataRepository
import com.progneo.smarthealth.data.util.latestHeartRate
import com.progneo.smarthealth.domain.model.HeartRateRecord
import com.progneo.smarthealth.domain.repository.HeartRateCacheRepository
import com.progneo.smarthealth.domain.repository.HeartRateRemoteRepository
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class PassiveDataService : PassiveListenerService() {

    @Inject
    lateinit var repository: PassiveDataRepository

    @Inject
    lateinit var heartRateCacheRepository: HeartRateCacheRepository

    @Inject
    @Named("api")
    lateinit var heartRateApiRepository: HeartRateRemoteRepository

    @Inject
    @Named("socket")
    lateinit var heartRateSocketRepository: HeartRateRemoteRepository

    override fun onNewDataPointsReceived(dataPoints: DataPointContainer) {
        runBlocking {
            dataPoints.getData(DataType.HEART_RATE_BPM).latestHeartRate()?.let {
                repository.storeLatestHeartRate(it)

                val record = HeartRateRecord(
                    rate = it,
                    timestamp = Date().time
                )

                if (!heartRateSocketRepository.sendHeartRateRecord(record)) {
                    heartRateCacheRepository.addRecord(record)
                } else {
                    checkCacheRecordsCount()
                }
            }
        }
    }

    private suspend fun checkCacheRecordsCount() {
        val cachedRecordsCount = heartRateCacheRepository.getCount()
        if (cachedRecordsCount != 0) {
            val recordList = heartRateCacheRepository.getAllRecords()
            if (!heartRateSocketRepository.sendHeartRateRecordList(recordList)) {
                heartRateCacheRepository.deleteAllRecords()
            }
        }
    }
}
