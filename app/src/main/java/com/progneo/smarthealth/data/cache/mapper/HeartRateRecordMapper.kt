package com.progneo.smarthealth.data.cache.mapper

import com.progneo.smarthealth.data.cache.model.CachedHeartRateRecord
import com.progneo.smarthealth.domain.model.HeartRateRecord

fun CachedHeartRateRecord.toDomain(): HeartRateRecord {
    return HeartRateRecord(
        rate = this.rate,
        timestamp = this.timestamp
    )
}

fun HeartRateRecord.toCache(): CachedHeartRateRecord {
    return CachedHeartRateRecord(
        rate = this.rate,
        timestamp = this.timestamp
    )
}
