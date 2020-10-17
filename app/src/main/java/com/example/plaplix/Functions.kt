package com.example.plaplix

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.animation.AlphaAnimation
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat

fun failToast(context: Context){
    Toast.makeText(context,"Failed Internet Request",Toast.LENGTH_SHORT).show()
}
@RequiresApi(Build.VERSION_CODES.N)
fun TextView.setString(id:Int, price: String?){
    val txt=context.getString(id,price)
    this.text= Html.fromHtml(txt, HtmlCompat.FROM_HTML_MODE_LEGACY)
}
val clickAnimation = AlphaAnimation(1f, 0.8f)