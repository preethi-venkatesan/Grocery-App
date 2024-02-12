package com.example.groceryapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GroceryViewModelFactory(private var repository: GroceryRepository) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GroceryViewModel(repository) as T
    }
}