package com.progneo.smarthealth.data.cache.util

import androidx.room.TypeConverter
import java.util.Date

class Convertors {

    @TypeConverter
    fun fromTimeStamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(value: Date?): Long? {
        return value?.let { value.time }
    }
}
