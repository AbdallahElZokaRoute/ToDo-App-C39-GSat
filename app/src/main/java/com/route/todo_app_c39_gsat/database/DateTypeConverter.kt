package com.route.todo_app_c39_gsat.database

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

class DateTypeConverter {

    @TypeConverter
    fun convertFromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun convertToDate(time: Long): Date {
        return Date(time)
    }

}