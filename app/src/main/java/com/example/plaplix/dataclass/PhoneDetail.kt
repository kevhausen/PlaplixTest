package com.example.plaplix.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_detail_table")
data class PhoneDetail(
    val credit: Boolean?,
    val description: String?,
    @PrimaryKey val id: Int?,
    val image: String?,
    val lastPrice: Int?,
    val name: String?,
    val price: Int?
)