package com.progneo.smarthealth

import android.app.Application
import com.progneo.smarthealth.data.HealthServicesRepository
import com.progneo.smarthealth.data.PassiveDataRepository

const val PERMISSION = android.Manifest.permission.BODY_SENSORS

class MainApplication : Application() {

    val healthServicesRepository by lazy { HealthServicesRepository(this) }
    val passiveDataRepository by lazy { PassiveDataRepository(this) }
}
