package com.example.swipe.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swipe.repo.ViewProductsRepo

class ViewProductViewModelFactory(private var repo:ViewProductsRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewProductViewModel(repo) as T
    }

}