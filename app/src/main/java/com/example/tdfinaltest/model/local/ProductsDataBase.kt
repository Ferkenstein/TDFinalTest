package com.example.tdfinaltest.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductsDataBase : RoomDatabase()  {

    abstract fun productsDAO(): ProductsDAO

    companion object{
        @Volatile
        private var INSTANCE : ProductsDataBase? = null

        fun getDataBase(context: Context): ProductsDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context,
                    ProductsDataBase::class.java,
                    "productsDataBase")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}