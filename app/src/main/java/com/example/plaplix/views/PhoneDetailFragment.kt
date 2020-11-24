package com.example.plaplix.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.plaplix.R
import com.example.plaplix.clickAnimation
import com.example.plaplix.dataclass.PhoneDetail
import com.example.plaplix.setString
import com.example.plaplix.viewmodel.ViewModelP
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_phone_detail.*


private const val ARG_PARAM1 = "param1"

class PhoneDetailFragment : Fragment() {
    private lateinit var viewmodelP : ViewModelP
    private var param1: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { param1 = it.getInt(ARG_PARAM1) }
        viewmodelP= ViewModelProvider(activity!!).get(ViewModelP::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_phone_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodelP.insertWebDetailDataIntoDB(param1)
        viewmodelP.getPhoneDetail(param1).observe(viewLifecycleOwner,{
            if(it!=null){
                setPhoneDetails(it)
            }
        })
    }


    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            PhoneDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, id)
                }
            }
    }

    private fun setPhoneDetails(phoneDetail:PhoneDetail){
            detail_phone_name.text=phoneDetail.name
            Picasso.get().load(phoneDetail.image).placeholder(R.drawable.ic_launcher_foreground).resize(500,500).centerCrop().into(detail_phone_image)
            val formattedPrice=String.format("%,d",phoneDetail.price).replace(',','.')
            val formattedLastPrice=String.format("%,d",phoneDetail.lastPrice).replace(',','.')
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                detail_last_price.setString(R.string.last_price_string,formattedLastPrice)
                detail_price.setString(R.string.actual_price_string,formattedPrice)
            }

            detail_phone_description.text=phoneDetail.description
            if(phoneDetail.credit!!){
                detail_credit.visibility=View.VISIBLE
                detail_credit_card_image.setImageDrawable(ContextCompat.getDrawable(activity!!,R.drawable.ic_credit_card))
                detail_credit_card_image.visibility=View.VISIBLE
            }
            val productName=phoneDetail.name
            val productId=phoneDetail.id
            detailRelativeLayout.setOnClickListener {
                it.startAnimation(clickAnimation)
                val intent= Intent(Intent.ACTION_SEND)
                val to = "info@plaplix.cl"
                val addressees = arrayOf(to)
                val subject= "Consulta $productName id $productId"
                val message = "Hola \nVi el producto $productName y me gustaría que me contactaran a este correo o al siguiente número _________ \nQuedo atento."
                intent.putExtra(Intent.EXTRA_EMAIL,addressees)
                intent.putExtra(Intent.EXTRA_SUBJECT,subject)
                intent.putExtra(Intent.EXTRA_TEXT,message)
                intent.type = "text/plain"
                startActivity(Intent.createChooser(intent,"Contactar Area de Ventas:"))

            }
    }
}