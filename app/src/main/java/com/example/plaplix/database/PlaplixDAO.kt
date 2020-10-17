package com.example.plaplix.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plaplix.dataclass.PhoneDetail
import com.example.plaplix.dataclass.PhoneP

@Dao
interface PlaplixDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhones(phonesList:List<PhoneP>)

    @Query("SELECT * FROM phone_table")
    fun getPhoneList():LiveData<List<PhoneP>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoneDetail(phoneDetail:PhoneDetail)
    
    @Query("SELECT * FROM phone_detail_table WHERE id=:idObtained")
    fun getPhoneDetail(idObtained:Int):LiveData<PhoneDetail>


}