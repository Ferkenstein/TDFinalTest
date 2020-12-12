package com.example.tdfinaltest.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(mList: List<ProductEntity>)

    @Query("SELECT * FROM products_table")
    fun showAllProducts(): LiveData<List<ProductEntity>>

    

}