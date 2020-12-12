package com.example.tdfinaltest.model.network

import com.example.tdfinaltest.model.pojos.DetailsProducts
import com.example.tdfinaltest.model.pojos.Products
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsAPI {
    // Mostrar listado de productos
    @GET("/Himuravidal/FakeAPIdata/products")
    suspend fun getAllProducts(): Response<List<Products>>

    // Mostrar detalle de productos por id
    @GET("/Himuravidal/FakeAPIdata/details/{id}")
    fun idDetails(@Path("id") idDetailsProducts: Int ):Response<DetailsProducts>

}