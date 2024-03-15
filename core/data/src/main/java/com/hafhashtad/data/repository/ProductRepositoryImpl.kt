package com.hafhashtad.data.repository

import com.hafhashtad.model.Product
import com.hafhashtad.network.retrofit.datasource.NetworkDataSource
import com.hafhashtad.network.retrofit.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: NetworkDataSource,
) : ProductRepository {
    override fun fetchProducts(): Flow<List<Product>> = flow {
        val remoteResponse = remoteDataSource.fetchProducts().map {
            it.asExternalModel()
        }
        emit(remoteResponse)
    }
}