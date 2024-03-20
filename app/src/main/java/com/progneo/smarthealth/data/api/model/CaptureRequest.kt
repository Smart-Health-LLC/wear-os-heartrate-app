package com.progneo.smarthealth.data.api.model

import java.util.Date

data class CaptureRequest(
    val userId: Int,
    val startTime: Date?,
    val endTime: Date,
    val typeName: CaptureType,
    val value: Double
)
