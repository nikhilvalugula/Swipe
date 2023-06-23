package com.example.swipe.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.swipe.R
import com.example.swipe.databinding.ItemViewProductBinding
import com.example.swipe.db.ProductDataItem

class SearchAdapter() :
    RecyclerView.Adapter<SearchAdapter.SearchViewHOlder>() {
    private val products: MutableList<ProductDataItem> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHOlder {
        val binding =
            ItemViewProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHOlder(binding)
    }

    override fun getItemCount(): Int = products.size


    @SuppressLint("NotifyDataSetChanged")
    fun setData(productsList: List<ProductDataItem>) {
        products.addAll(productsList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SearchViewHOlder, position: Int) {
        val productItem = products[position]
        holder.bind(productItem)
    }

    class SearchViewHOlder(var binding: ItemViewProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: ProductDataItem) {
            binding.productName.text = "name: " + product.product_name
            binding.productPrice.text = "$ " + product.price.toString()
            binding.productTax.text = "tax: " + product.tax.toString()
            binding.productType.text = "type: " + product.product_type
            Glide.with(this.binding.root.context)
                .load(product.image)
                .placeholder(R.drawable.image_placeholder_2)
                .fitCenter()
                .into(binding.productImage)
        }

    }

}