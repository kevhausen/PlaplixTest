package com.example.plaplix.views

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.plaplix.R
import com.example.plaplix.dataclass.PhoneP
import com.example.plaplix.setString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.viewholder_phone.view.*



class PhoneListAdapter(var iAdapter:IPhoneListAdapter):RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder>() {
    private var mDataset= emptyList<PhoneP>()

    fun updateDataset(phoneList:List<PhoneP>){
        mDataset=phoneList
        notifyDataSetChanged()
    }

    inner class PhoneViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(phones:PhoneP){
            itemView.phone_name.text=phones.name
            val formattedPrice=String.format("%,d",phones.price).replace(',','.')
            itemView.phone_price.setString(R.string.price,formattedPrice)
            Picasso.get().load(phones.image).placeholder(R.drawable.ic_launcher_foreground).resize(600,600).centerCrop().into(itemView.phone_image)

            itemView.setOnClickListener {
                phones.id?.let { n -> iAdapter.idFromPhone(n) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        return PhoneViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_phone,parent,false))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bind(mDataset[position])

    }

    override fun getItemCount(): Int {
        return mDataset.size
    }


    interface IPhoneListAdapter{
        fun idFromPhone(id:Int)
    }
}