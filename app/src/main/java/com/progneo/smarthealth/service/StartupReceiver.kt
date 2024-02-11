package com.progneo.smarthealth.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.progneo.smarthealth.PERMISSION
import com.progneo.smarthealth.data.repository.HealthServicesRepository
import com.progneo.smarthealth.data.repository.PassiveDataRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class StartupReceiver : BroadcastReceiver() {

    @Inject
    lateinit var passiveDataRepository: PassiveDataRepository

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED) return

        runBlocking {
            if (passiveDataRepository.passiveDataEnabled.first()) {
                val result = context.checkSelfPermission(PERMISSION)
                if (result == PackageManager.PERMISSION_GRANTED) {
                    scheduleWorker(context)
                } else {
                    passiveDataRepository.setPassiveDataEnabled(false)
                }
            }
        }
    }

    private fun scheduleWorker(context: Context) {
        Log.i(javaClass.simpleName, "Enqueuing worker")
        WorkManager.getInstance(context).enqueue(
            OneTimeWorkRequestBuilder<RegisterForBackgroundDataWorker>().build()
        )
    }
}

@HiltWorker
class RegisterForBackgroundDataWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    @Inject
    lateinit var healthServicesRepository: HealthServicesRepository

    override suspend fun doWork(): Result {
        Log.i(javaClass.simpleName, "Worker running")
        healthServicesRepository.registerForHeartRateData()
        return Result.success()
    }
}
