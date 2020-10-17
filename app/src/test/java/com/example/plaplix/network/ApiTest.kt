package com.example.plaplix.network

import com.example.plaplix.dataclass.PhoneP
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {
    lateinit var mMockWebServer: MockWebServer
    lateinit var plaplixAPI : PlaplixAPI

    @Before
    fun setUp() {
        mMockWebServer = MockWebServer()
        val retro = Retrofit.Builder()
            .baseUrl(mMockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        plaplixAPI =  retro.create(PlaplixAPI::class.java)
    }

    @After
    fun shutDown() {
        mMockWebServer.shutdown()
    }

    @Test
    fun getProducts() = runBlocking {
        //given
        val mResultList = listOf<PhoneP>(PhoneP(1,"https:www.afasda.png","samsung galaxy",149990))
        mMockWebServer.enqueue(MockResponse().setBody(Gson().toJson(mResultList)))

        //when
        val result = plaplixAPI.getProducts()
        //then
        assertThat(result).isNotNull()

    }
}