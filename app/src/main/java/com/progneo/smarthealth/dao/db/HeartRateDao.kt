package com.progneo.smarthealth.dao.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.progneo.smarthealth.dao.model.CachedHeartRate

@Dao
interface HeartRateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecord(entity: CachedHeartRate): Long

    @Query("select * from heart_rate")
    fun getAllRecords(): List<CachedHeartRate>

    @Query("delete from heart_rate")
    fun deleteAllRecords()
}
