package com.example.tdfinaltest.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tdfinaltest.model.ProductsRepository
import com.example.tdfinaltest.model.local.ProductEntity
import com.example.tdfinaltest.model.local.ProductsDataBase

class ProductsViewModel(application: Application) : AndroidViewModel(application){
    private val mRepository: ProductsRepository
    val liveDataFromLocal : LiveData<List<ProductEntity>>

    init {
        val productsDAO = ProductsDataBase.getDataBase(application).productsDAO()
        mRepository = ProductsRepository(productsDAO)
        mRepository.getAllProductsFromServer()
        liveDataFromLocal = mRepository.allProductsLiveData
    }
}