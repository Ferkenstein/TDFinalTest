package com.example.tdfinaltest.model

import android.util.Log
import com.example.tdfinaltest.model.local.ProductEntity
import com.example.tdfinaltest.model.local.ProductsDAO
import com.example.tdfinaltest.model.network.RetrofitClient
import com.example.tdfinaltest.model.pojos.Products
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class ProductsRepository(private val productsDAO: ProductsDAO) {

    // Union de la API con el cliente Retrofit
    private val retroService = RetrofitClient.getRetrofitClient()
    // Traer tabla de peliculas Entity
    val allProductsLiveData = productsDAO.showAllProducts()

    fun getAllProductsFromServer() = CoroutineScope(Dispatchers.IO).launch{
        val retroService = kotlin.runCatching { retroService.getAllProducts() }
        retroService.onSuccess {

            when(it.code()){
                in 200..299 -> it.body()?.let {
                    productsDAO.insertAllProducts(convert(it))
                }
                in 300..599 -> Log.d("RESPONSE_300-", it.body().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
        }
        retroService.onFailure {
            Log.e("ERROR", it.message.toString())
        }
    }
    fun convert(listFromNetwork:List<Products>): List<ProductEntity> {
        val listMutable = mutableListOf<ProductEntity>()

        listFromNetwork.map {
            listMutable.add(ProductEntity(it.id,
                it.image,
                it.name,
                it.price))
        }
        return listMutable
    }


}
