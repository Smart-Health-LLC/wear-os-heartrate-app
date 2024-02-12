package com.progneo.smarthealth.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.progneo.smarthealth.data.cache.model.CachedHeartRateRecord
import com.progneo.smarthealth.data.cache.util.Convertors

@Database(entities = [CachedHeartRateRecord::class], version = 1)
@TypeConverters(Convertors::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun heartTateDao(): HeartRateDao

    companion object {
        const val DATABASE_NAME = "heart_rate_app"
    }
}
