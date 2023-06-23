package com.example.swipe.networkclient

import com.example.swipe.db.ProductDataItem
import com.example.swipe.db.PushResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitClient {

    @GET(value = "get")
    suspend fun fetchProducts(): Response<List<ProductDataItem>>

    @GET(value = "get")
    suspend fun getSearchPost(
        @Query("product_type") productType: String
    ): Response<List<ProductDataItem>>

    @FormUrlEncoded
    @POST(value = "add")
    suspend fun setProductDetails(
        @Field("product_name") productName: String,
        @Field("product_type") productType: String,
        @Field("price") productPrice: Double,
        @Field("tax") productTax: Double,
    ): Response<PushResponse>

    companion object {

        private const val BASE_URL = "https://app.getswipe.in/api/public/"

        private var retrofitClient: RetrofitClient? = null

        fun getInstance(): RetrofitClient {

            if (retrofitClient == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitClient = retrofit.create(RetrofitClient::class.java)
            }
            return retrofitClient!!
        }
    }
}