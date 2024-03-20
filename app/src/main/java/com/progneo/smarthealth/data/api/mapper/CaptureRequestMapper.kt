package com.progneo.smarthealth.data.api.mapper

import com.progneo.smarthealth.data.api.model.CaptureRequest
import com.progneo.smarthealth.data.api.model.CaptureType
import com.progneo.smarthealth.domain.model.HeartRateRecord
import java.util.Date

fun HeartRateRecord.toData(): CaptureRequest {
    return CaptureRequest(
        userId = 0,
        startTime = null,
        endTime = Date(),
        typeName = CaptureType.HEART_RATE,
        value = this.rate
    )
}
