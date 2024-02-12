package com.progneo.smarthealth.service

import android.util.Log
import androidx.health.services.client.PassiveListenerService
import androidx.health.services.client.data.DataPointContainer
import androidx.health.services.client.data.DataType
import com.progneo.smarthealth.data.datasource.HeartRateCacheDatasource
import com.progneo.smarthealth.data.entity.HeartRate
import com.progneo.smarthealth.data.repository.PassiveDataRepository
import com.progneo.smarthealth.data.util.latestHeartRate
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class PassiveDataService : PassiveListenerService() {

    @Inject
    lateinit var repository: PassiveDataRepository

    @Inject
    lateinit var heartRateDatasource: HeartRateCacheDatasource

    override fun onNewDataPointsReceived(dataPoints: DataPointContainer) {
        runBlocking {
            dataPoints.getData(DataType.HEART_RATE_BPM).latestHeartRate()?.let {
                repository.storeLatestHeartRate(it)
                heartRateDatasource.addRecord(HeartRate(it, Date()))
                Log.i(javaClass.simpleName, heartRateDatasource.getAllRecords().toString())
            }
        }
    }
}
