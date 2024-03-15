package com.hafhashtad.network.retrofit.datasource

import com.hafhashtad.network.retrofit.model.NetworkProduct


interface NetworkDataSource {

    suspend fun fetchProducts(): List<NetworkProduct>
}