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
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class PassiveDataService : PassiveListenerService() {

    @Inject
    lateinit var repository: PassiveDataRepository

    @Inject
    lateinit var heartRateCacheRepository: HeartRateCacheRepository

    @Inject
    lateinit var heartRateRemoteRepository: HeartRateRemoteRepository

    override fun onNewDataPointsReceived(dataPoints: DataPointContainer) {
        runBlocking {
            dataPoints.getData(DataType.HEART_RATE_BPM).latestHeartRate()?.let {
                repository.storeLatestHeartRate(it)
                heartRateCacheRepository.addRecord(HeartRateRecord(it, Date().time))

                val recordList = heartRateCacheRepository.getAllRecords()
                if (recordList.size >= 100) {
                    val result = heartRateRemoteRepository.sendHeartRateRecordList(recordList)
                    if (result) {
                        heartRateCacheRepository.deleteAllRecords()
                    }
                }
            }
        }
    }
}
