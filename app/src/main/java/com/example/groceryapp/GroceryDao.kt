package com.example.groceryapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface GroceryDao {
    @Query("SELECT * FROM grocery_items")
    fun getAllGroceryItems() : LiveData<List<GroceryItems>>

    @Delete
    suspend fun delete(items: GroceryItems)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: GroceryItems)
}