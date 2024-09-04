package com.example.apptodo.dateconvert

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date


class Dateconvert {
    @TypeConverter
    fun fromdate(date: Date): Long {
        return date.time
    }
    @TypeConverter
    fun todate(millisSinceEpoch: Long): Date {
        return Date(millisSinceEpoch)
    }
}