package com.example.swipe.repo

import com.example.swipe.db.PushResponse
import com.example.swipe.networkclient.RetrofitClient
import com.example.swipe.networkclient.SafeApiRequest

class AddProductsRepo(private var api: RetrofitClient) : SafeApiRequest() {

    suspend fun pushProducts(
        productName: String,
        productType: String,
        ProductPrice: Double,
        ProductTax: Double
    ): PushResponse {
        return apiRequest {
            api.setProductDetails(
                productName,
                productType,
                ProductPrice,
                ProductTax
            )
        }
    }


}

