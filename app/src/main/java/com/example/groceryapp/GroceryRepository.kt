package com.example.groceryapp

import androidx.lifecycle.LiveData

class GroceryRepository(private var db: GroceryDatabase) {

    suspend fun insert(items: GroceryItems) = db.getGroceryDao().insert(items)
    suspend fun delete(items: GroceryItems) = db.getGroceryDao().delete(items)


    fun getAllItems() : LiveData<List<GroceryItems>> = db.getGroceryDao().getAllGroceryItems()
}