package com.example.swipe.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.swipe.db.ProductDataItem
import com.example.swipe.repo.ViewProductsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ViewProductViewModel(var repo: ViewProductsRepo) : ViewModel() {

    suspend fun fetchProducts(): LiveData<List<ProductDataItem>> {
        return withContext(Dispatchers.IO) {
            repo.fetchAllProducts()
        }
    }

    suspend fun getSearchPosts(productType: String): LiveData<List<ProductDataItem>> {
        return withContext(Dispatchers.IO) {
            repo.getSearchPost(productType)
        }
    }


}