package com.progneo.smarthealth.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.progneo.smarthealth.data.cache.model.CachedHeartRateRecord

@Dao
interface HeartRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecord(entity: CachedHeartRateRecord): Long

    @Query("select * from heart_rate")
    fun getAllRecords(): List<CachedHeartRateRecord>

    @Query("delete from heart_rate")
    fun deleteAllRecords()

    @Query("select count(id) from heart_rate")
    fun getCount(): Int
}
