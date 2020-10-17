package com.example.plaplix.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.plaplix.dataclass.PhoneDetail
import com.example.plaplix.dataclass.PhoneP
import com.example.plaplix.repository.RepositoryP

class ViewModelP(application: Application): AndroidViewModel(application) {
    private val repositoryP=RepositoryP(application)

    private fun insertWebDataIntoDB(){
        repositoryP.insertWebDataIntoDB()
    }
    init {
        insertWebDataIntoDB()
    }

    fun getPhoneList():LiveData<List<PhoneP>>{
        return repositoryP.getPhoneList()
    }

    fun insertWebDetailDataIntoDB(id:Int){
        repositoryP.insertWebDetailDataIntoDB(id)
    }

    fun getPhoneDetail(id:Int):LiveData<PhoneDetail>{
        return repositoryP.getPhoneDetail(id)
    }
}