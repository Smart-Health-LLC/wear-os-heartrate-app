package com.progneo.smarthealth.dao.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.progneo.smarthealth.dao.model.CachedHeartRate

@Database(entities = [CachedHeartRate::class], version = 1)
@TypeConverters(Convertors::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun heartTateDao(): HeartRateDao

    companion object {
        const val DATABASE_NAME = "heart_rate_app"
    }
}
