package com.hafhashtad.network.retrofit.datasource

import com.hafhashtad.network.retrofit.api.NetworkApi
import com.hafhashtad.network.retrofit.model.NetworkProduct
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val networkApi: NetworkApi
) : NetworkDataSource {
    override suspend fun fetchProducts(): List<NetworkProduct> {
        return networkApi.fetchProducts()
    }

}