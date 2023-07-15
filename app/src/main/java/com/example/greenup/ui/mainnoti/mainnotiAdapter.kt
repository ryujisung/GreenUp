package com.example.greenup.ui.mainnoti

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.greenup.R
import com.example.greenup.model.data.NotiData


class NotiAdapter(private val context: Context) : RecyclerView.Adapter<NotiAdapter.ViewHolder>() {

    var datas = mutableListOf<NotiData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_main_noti,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtTitle: TextView = itemView.findViewById(R.id.txt_mainnoti_title)
        private val txtTime: TextView = itemView.findViewById(R.id.txt_mainnoti_time)
        private val txtContexttile: TextView = itemView.findViewById(R.id.txt_mainnoti_comtexttitle)
        private val imgProfile: ImageView = itemView.findViewById(R.id.img_mainnoti_headLogo)

        fun bind(item: NotiData) {
            txtTitle.text = item.title
            txtTime.text = item.time
            txtContexttile.text = item.contexttile
            Glide.with(itemView).load(item.img).into(imgProfile)

        }
    }


}