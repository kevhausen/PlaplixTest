package com.example.plaplix.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.plaplix.dataclass.PhoneDetail
import com.example.plaplix.dataclass.PhoneP

@Database(entities = [PhoneP::class, PhoneDetail::class],version = 1)
//@TypeConverters(PhonePConverter::class)
abstract class PlaplixDB:RoomDatabase() {
        abstract fun daoPlaplix():PlaplixDAO
        companion object{
         @Volatile
            private var INSTANCE: PlaplixDB?=null

            fun getMovieDB(context : Context):PlaplixDB{
                val createdInstance = INSTANCE
             if(createdInstance!=null){
                  return createdInstance
              }
                synchronized(this){
                  val newInstance = Room.databaseBuilder(context.applicationContext,PlaplixDB::class.java,"movies_db")
                     .build()
                 INSTANCE=newInstance
                 return newInstance
              }
           }
     }
}