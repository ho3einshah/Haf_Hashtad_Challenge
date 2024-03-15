package com.hafhashtad.data.repository

import com.hafhashtad.common.network.Dispatcher
import com.hafhashtad.common.network.HafHashtadDispatchers
import com.hafhashtad.model.Product
import com.hafhashtad.network.retrofit.datasource.NetworkDataSource
import com.hafhashtad.network.retrofit.model.asExternalModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: NetworkDataSource,
    @Dispatcher(HafHashtadDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : ProductRepository {
    override fun fetchProducts(): Flow<List<Product>> = flow {
        val remoteResponse = remoteDataSource.fetchProducts().map {
            it.asExternalModel()
        }
        emit(remoteResponse)
    }.flowOn(ioDispatcher)
}