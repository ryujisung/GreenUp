package com.example.greenup.ui.moreeco

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.greenup.R
import com.example.greenup.model.data.MoreData
import com.example.greenup.model.data.NotiData


class EcoMoreAdapter(private val context: Context) : RecyclerView.Adapter<EcoMoreAdapter.ViewHolder>() {

    var datas = mutableListOf<MoreData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_main_more,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtTitle: TextView = itemView.findViewById(R.id.txt_more_title)
        private val txtContexttile: TextView = itemView.findViewById(R.id.txt_more_comtexttitle )

        fun bind(item: MoreData) {
            txtTitle.text = item.title
            txtContexttile.text = item.contexttile

        }
    }


}