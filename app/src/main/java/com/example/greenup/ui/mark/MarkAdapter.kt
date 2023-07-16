package com.example.greenup.ui.mark

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.greenup.R
import com.example.greenup.model.data.MarkData

class MarkAdapter(private val context: Context) : RecyclerView.Adapter<MarkAdapter.ViewHolder>() {

    var datas = mutableListOf<MarkData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_search_mark,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtTitle: TextView = itemView.findViewById(R.id.txt_mark_title)
        private val txtContexttile: TextView = itemView.findViewById(R.id.txtmark_comtexttitle)
        private val imgProfile: ImageView = itemView.findViewById(R.id.img_mark_headLogo)

        fun bind(item: MarkData) {
            txtTitle.text = item.title
            txtContexttile.text = item.contexttile
            Glide.with(itemView).load(item.img).into(imgProfile)

        }
    }


}