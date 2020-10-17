package com.example.plaplix.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.plaplix.database.PlaplixDB
import com.example.plaplix.dataclass.PhoneDetail
import com.example.plaplix.dataclass.PhoneP
import com.example.plaplix.failToast
import com.example.plaplix.network.RetrofitP
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryP(context: Context) {
    val retrofit = RetrofitP.retrofitInstance()
    val dao=PlaplixDB.getMovieDB(context).daoPlaplix()
    val mContext=context

    fun insertWebDataIntoDB(){
        retrofit.getProducts().enqueue(object: Callback<List<PhoneP>> {
            override fun onResponse(
                call: Call<List<PhoneP>>,
                response: Response<List<PhoneP>>
            ) {
                response.body()?.let {
                    CoroutineScope(IO).launch { dao.insertPhones(it) }
                    }
            }

            override fun onFailure(call: Call<List<PhoneP>>, t: Throwable) {
                failToast(mContext)
            }
        })
    }

    fun getPhoneList():LiveData<List<PhoneP>>{
        return dao.getPhoneList()
    }

    fun insertWebDetailDataIntoDB(id:Int){
        retrofit.getPhoneDetail(id).enqueue(object : Callback<PhoneDetail>{
            override fun onResponse(call: Call<PhoneDetail>, response: Response<PhoneDetail>) {
                response.body()?.let {
                    CoroutineScope(IO).launch { dao.insertPhoneDetail(it) }
                }
            }

            override fun onFailure(call: Call<PhoneDetail>, t: Throwable) {
                failToast(mContext)
            }
        })
    }

    fun getPhoneDetail(id:Int):LiveData<PhoneDetail>{
        return dao.getPhoneDetail(id)
    }



}