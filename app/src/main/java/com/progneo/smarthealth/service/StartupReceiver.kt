package com.progneo.smarthealth.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.progneo.smarthealth.PERMISSION
import com.progneo.smarthealth.data.HealthServicesRepository
import com.progneo.smarthealth.data.PassiveDataRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class StartupReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val repository = PassiveDataRepository(context)
        if (intent.action != Intent.ACTION_BOOT_COMPLETED) return

        runBlocking {
            if (repository.passiveDataEnabled.first()) {
                val result = context.checkSelfPermission(PERMISSION)
                if (result == PackageManager.PERMISSION_GRANTED) {
                    scheduleWorker(context)
                } else {
                    repository.setPassiveDataEnabled(false)
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

class RegisterForBackgroundDataWorker(
    private val appContext: Context,
    workerParams: WorkerParameters
) :

    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        Log.i(javaClass.simpleName, "Worker running")
        val healthServicesRepository = HealthServicesRepository(appContext)
        healthServicesRepository.registerForHeartRateData()
        return Result.success()
    }
}
