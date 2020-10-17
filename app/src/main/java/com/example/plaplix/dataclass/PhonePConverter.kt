package com.example.plaplix.dataclass

import androidx.room.TypeConverter
import com.google.gson.Gson

class PhonePConverter {
    @TypeConverter
    fun listToJson(value: List<PhoneP>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<PhoneP>? {
        val objects = Gson().fromJson(value, Array<PhoneP>::class.java) as Array<PhoneP>
        val list = objects.toList()
        return list
    }
}