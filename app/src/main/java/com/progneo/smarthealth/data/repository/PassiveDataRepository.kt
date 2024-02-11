package com.progneo.smarthealth.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "passive_data")

interface PassiveDataRepository {

    val passiveDataEnabled: Flow<Boolean>

    suspend fun setPassiveDataEnabled(enabled: Boolean)

    val latestHeartRate: Flow<Double>
    suspend fun storeLatestHeartRate(heartRate: Double)
}

@Singleton
internal class PassiveDataRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : PassiveDataRepository {

    private val passiveDataStore = context.dataStore

    override val passiveDataEnabled: Flow<Boolean> = passiveDataStore.data.map { prefs ->
        prefs[PASSIVE_DATA_ENABLED] ?: false
    }

    override suspend fun setPassiveDataEnabled(enabled: Boolean) {
        passiveDataStore.edit { prefs ->
            prefs[PASSIVE_DATA_ENABLED] = enabled
        }
    }

    override val latestHeartRate: Flow<Double> = passiveDataStore.data.map { prefs ->
        prefs[LATEST_HEART_RATE] ?: 0.0
    }

    override suspend fun storeLatestHeartRate(heartRate: Double) {
        passiveDataStore.edit { prefs ->
            prefs[LATEST_HEART_RATE] = heartRate
        }
    }

    companion object {
        private val PASSIVE_DATA_ENABLED = booleanPreferencesKey("passive_data_enabled")
        private val LATEST_HEART_RATE = doublePreferencesKey("latest_heart_rate")
    }
}
