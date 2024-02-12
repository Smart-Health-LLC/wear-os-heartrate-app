package com.progneo.smarthealth.dao.mapper

import com.progneo.smarthealth.dao.model.CachedHeartRate
import com.progneo.smarthealth.data.entity.HeartRate
import javax.inject.Inject

class HeartRateMapper @Inject constructor() : EntityMapper<CachedHeartRate, HeartRate> {

    override fun mapFromCached(model: CachedHeartRate): HeartRate {
        return HeartRate(
            model.rate,
            model.date
        )
    }

    override fun mapToCached(model: HeartRate): CachedHeartRate {
        return CachedHeartRate(
            rate = model.rate,
            date = model.date
        )
    }
}
