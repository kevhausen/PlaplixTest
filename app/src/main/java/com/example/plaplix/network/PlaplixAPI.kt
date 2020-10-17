package com.example.plaplix.network

import com.example.plaplix.dataclass.PhoneDetail
import com.example.plaplix.dataclass.PhoneP
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaplixAPI {
    @GET("products")
    fun getProducts(): Call<List<PhoneP>>

    @GET("details/{id}")
    fun getPhoneDetail(@Path("id")id:Int):Call<PhoneDetail>

}