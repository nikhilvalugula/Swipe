package com.example.swipe.viewModels

import androidx.lifecycle.ViewModel
import com.example.swipe.repo.AddProductsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddProductViewModel(var repo: AddProductsRepo) : ViewModel() {

    suspend fun pushPosts(
        productName: String,
        productType:String,
        ProductPrice: Double,
        ProductTax: Double
    ) = withContext(Dispatchers.IO) {
            repo.pushProducts(productName,productType, ProductPrice, ProductTax)
        }

}