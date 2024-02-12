package com.progneo.smarthealth.dao.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "heart_rate")
data class CachedHeartRate(
    var rate: Double,
    var date: Date
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
