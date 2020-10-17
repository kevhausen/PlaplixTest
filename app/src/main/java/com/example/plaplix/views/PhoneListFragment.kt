package com.example.plaplix.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plaplix.R
import com.example.plaplix.viewmodel.ViewModelP
import kotlinx.android.synthetic.main.fragment_phone_list.*


class PhoneListFragment : Fragment(),PhoneListAdapter.IPhoneListAdapter {
    private lateinit var viewmodelP :ViewModelP
    private lateinit var mAdapter:PhoneListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodelP=ViewModelProvider(activity!!).get(ViewModelP::class.java)
        mAdapter=PhoneListAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_phone_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodelP.getPhoneList().observe(viewLifecycleOwner,{
            mAdapter.updateDataset(it)
        })
        recycler_phone_list.apply {
            adapter=mAdapter
            layoutManager=GridLayoutManager(activity,3)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PhoneListFragment()
    }

    override fun idFromPhone(id: Int) {
        (activity as NavigationHost?)!!.navigateTo(PhoneDetailFragment.newInstance(id), true)
    }
}