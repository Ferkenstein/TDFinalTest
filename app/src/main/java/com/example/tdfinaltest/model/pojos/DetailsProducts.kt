package com.example.tdfinaltest.model.pojos


import com.google.gson.annotations.SerializedName

data class DetailsProducts(
    @SerializedName("credit")
    val credit: Boolean,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("lastPrice")
    val lastPrice: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)