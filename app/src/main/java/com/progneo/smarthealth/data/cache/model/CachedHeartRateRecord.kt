package com.progneo.smarthealth.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heart_rate")
data class CachedHeartRateRecord(
    var rate: Double,
    var timestamp: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
