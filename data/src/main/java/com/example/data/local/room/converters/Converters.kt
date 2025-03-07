package com.example.data.local.room.converters

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromList(list: List<String>?): String {
        return list?.joinToString(separator = ",") ?: ""
    }

    @TypeConverter
    fun toList(data: String?): List<String> {
        return data?.split(",") ?: emptyList()
    }
}