package com.hafhashtad.network.retrofit.api

import com.hafhashtad.network.retrofit.model.NetworkProduct
import retrofit2.http.GET

interface NetworkApi {
    @GET("products")
    suspend fun fetchProducts(
    ): List<NetworkProduct>
}