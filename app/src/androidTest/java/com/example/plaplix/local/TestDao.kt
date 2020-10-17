package com.example.plaplix.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.plaplix.database.PlaplixDAO
import com.example.plaplix.database.PlaplixDB
import com.example.plaplix.dataclass.PhoneP
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestDao {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var plaplixDao: PlaplixDAO
    private lateinit var db: PlaplixDB

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PlaplixDB::class.java).build()
        plaplixDao = db.daoPlaplix()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertPhones() = runBlocking {
        //given
        val phoneList = listOf(PhoneP(1,"https:www.afasda.png","samsung galaxy",149990))

        // when
        plaplixDao.insertPhones(phoneList)

        //then
        plaplixDao.getPhoneList().observeForever{
            assertThat(it).isNotNull()
            println(it.toString())
            assertThat(it[0].name).isEqualTo("samsung galaxy")

        }
    }
}