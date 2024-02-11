package com.progneo.smarthealth

import android.app.Application
import com.progneo.smarthealth.data.repository.HealthServicesRepository
import com.progneo.smarthealth.data.repository.PassiveDataRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

const val PERMISSION = android.Manifest.permission.BODY_SENSORS

@HiltAndroidApp
class MainApplication : Application() {

    @Inject
    lateinit var healthServicesRepository: HealthServicesRepository

    @Inject
    lateinit var passiveDataRepository: PassiveDataRepository
}
