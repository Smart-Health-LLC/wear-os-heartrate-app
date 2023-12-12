package com.progneo.smarthealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.progneo.smarthealth.presentation.WearApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        val healthServicesRepository = (application as MainApplication).healthServicesRepository
        val passiveDataRepository = (application as MainApplication).passiveDataRepository

        setContent {
            WearApp(
                healthServicesRepository = healthServicesRepository,
                passiveDataRepository = passiveDataRepository
            )
        }
    }
}
