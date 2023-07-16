package com.example.greenup.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.greenup.R
import com.example.greenup.model.remote.FoodSearch
import com.example.greenup.databinding.ItemFoodSearchBinding

class FoodSerchAdapter : RecyclerView.Adapter<FoodSerchAdapter.ViewHolder>() {
    var items = ArrayList<FoodSearch>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFoodSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            bind(item)
            itemView.tag = item
        }
    }

    fun updateItems(items: List<FoodSearch>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemFoodSearchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: FoodSearch) {
            binding.productName.text = product.name
            Glide.with(binding.root).load(product.img_link).into(binding.productImage)
        }
    }

}
