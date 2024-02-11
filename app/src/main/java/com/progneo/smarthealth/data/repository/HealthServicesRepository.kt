package com.progneo.smarthealth.data.repository

import android.content.Context
import android.util.Log
import androidx.concurrent.futures.await
import androidx.health.services.client.HealthServices
import androidx.health.services.client.data.DataType
import androidx.health.services.client.data.PassiveListenerConfig
import com.progneo.smarthealth.service.PassiveDataService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

interface HealthServicesRepository {

    suspend fun hasHeartRateCapability(): Boolean

    suspend fun registerForHeartRateData()

    suspend fun unregisterForHeartRateData()
}

@Singleton
internal class HealthServicesRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : HealthServicesRepository {

    private val healthServicesClient = HealthServices.getClient(context)
    private val passiveMonitoringClient = healthServicesClient.passiveMonitoringClient
    private val dataTypes = setOf(DataType.HEART_RATE_BPM)

    private val passiveListenerConfig = PassiveListenerConfig(
        dataTypes = dataTypes,
        shouldUserActivityInfoBeRequested = false,
        dailyGoals = setOf(),
        healthEventTypes = setOf()
    )

    override suspend fun hasHeartRateCapability(): Boolean {
        val capabilities = passiveMonitoringClient.getCapabilitiesAsync().await()
        return DataType.HEART_RATE_BPM in capabilities.supportedDataTypesPassiveMonitoring
    }

    override suspend fun registerForHeartRateData() {
        Log.i(javaClass.simpleName, "Registering listener")
        passiveMonitoringClient.setPassiveListenerServiceAsync(
            PassiveDataService::class.java,
            passiveListenerConfig
        ).await()
    }

    override suspend fun unregisterForHeartRateData() {
        Log.i(javaClass.simpleName, "Unregistering listeners")
        passiveMonitoringClient.clearPassiveListenerServiceAsync().await()
    }
}
