package com.progneo.smarthealth.data.util

import androidx.health.services.client.data.DataType
import androidx.health.services.client.data.HeartRateAccuracy
import androidx.health.services.client.data.HeartRateAccuracy.SensorStatus.Companion.ACCURACY_HIGH
import androidx.health.services.client.data.HeartRateAccuracy.SensorStatus.Companion.ACCURACY_MEDIUM
import androidx.health.services.client.data.SampleDataPoint

fun List<SampleDataPoint<Double>>.latestHeartRate(): Double? {
    return this
        .filter { it.dataType == DataType.HEART_RATE_BPM }
        .filter {
            it.accuracy == null ||
                setOf(
                    ACCURACY_HIGH,
                    ACCURACY_MEDIUM
                ).contains((it.accuracy as HeartRateAccuracy).sensorStatus)
        }
        .filter {
            it.value > 0
        }
        .maxByOrNull { it.timeDurationFromBoot }?.value
}
