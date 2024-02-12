package com.example.groceryapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private var repository: GroceryRepository) : ViewModel() {

    fun insert(items: GroceryItems)= GlobalScope.launch {

        repository.insert(items)
    }

    fun delete(items: GroceryItems)= GlobalScope.launch {

        repository.delete(items)
    }

    fun getAllGroceryItems() : LiveData<List<GroceryItems>> {

        return repository.getAllItems()
    }

}