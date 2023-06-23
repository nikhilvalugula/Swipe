package com.example.swipe.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.swipe.db.ProductDataItem
import com.example.swipe.networkclient.RetrofitClient
import com.example.swipe.networkclient.SafeApiRequest

class ViewProductsRepo(private var api: RetrofitClient) : SafeApiRequest() {

    suspend fun fetchAllProducts(): LiveData<List<ProductDataItem>> {
        val response = apiRequest { api.fetchProducts() }
        return MutableLiveData(response)
    }

    suspend fun getSearchPost(productType:String): LiveData<List<ProductDataItem>> {
        val response = apiRequest { api.getSearchPost(productType) }
        return MutableLiveData(response)
    }


}