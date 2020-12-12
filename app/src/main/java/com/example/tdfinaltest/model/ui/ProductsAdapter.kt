package com.example.tdfinaltest.model.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tdfinaltest.R
import com.example.tdfinaltest.model.local.ProductEntity
import kotlinx.android.synthetic.main.products_item_list.view.*

class ProductsAdapter(val mPassTheData: PassTheData): RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private var productsList = emptyList<ProductEntity>()

    fun updateAdapter(mList: List<ProductEntity>) {
        productsList = mList
        notifyDataSetChanged()
    }

    inner class ProductsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image = itemView.productsImage
        val name = itemView.nameProduct
        val price = itemView.price
        val clickListener = itemView.setOnClickListener {
            mPassTheData.passProducts(productsList[adapterPosition])
        }
    }
    // Proyecta en la vista cada uno de los elementos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.products_item_list,parent,false)
        return ProductsViewHolder(view)
    }

    // Une la vista con los elemetos del listado
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val products = productsList[position]
        Glide.with(holder.itemView.context).load(products.image).into(holder.image)
        holder.name.text = products.name
        holder.price.text = products.price.toString()
    }

    override fun getItemCount() = productsList.size

    interface PassTheData {
        fun passProducts(products : ProductEntity)
    }

}