package com.example.plaplix.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_table")
data class PhoneP(
    @PrimaryKey val id: Int?,
    val image: String?,
    val name: String?,
    val price: Int?
)