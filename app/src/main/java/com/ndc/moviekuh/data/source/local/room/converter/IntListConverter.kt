package com.ndc.moviekuh.data.source.local.room.converter

import androidx.room.TypeConverter

class RoomConverters {

    @TypeConverter
    fun fromListInt(list: List<Int>): String {
        return list.joinToString { it.toString() }
    }

    @TypeConverter
    fun toListInt(value: String): List<Int> {
        return value.split(", ").map { it.toInt() }
    }
}
