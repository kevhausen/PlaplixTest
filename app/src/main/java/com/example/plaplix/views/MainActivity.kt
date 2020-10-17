package com.example.plaplix.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.plaplix.R
import com.example.plaplix.viewmodel.ViewModelP

class MainActivity : AppCompatActivity(),NavigationHost {

    private lateinit var plaplixViewModel:ViewModelP
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateTo(PhoneListFragment.newInstance(),false)

    }

    override fun navigateTo(fragment: Fragment, boolean: Boolean) {
        val transaction = supportFragmentManager.beginTransaction().replace(R.id.frame_main,fragment)
        if(boolean){
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

}