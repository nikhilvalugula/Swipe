package com.example.swipe.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swipe.repo.AddProductsRepo

class AddProductViewModelFactory(private var repo: AddProductsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddProductViewModel(repo) as T
    }

}