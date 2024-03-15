package com.hafhashtad.data.repository

import com.hafhashtad.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun fetchProducts(): Flow<List<Product>>

}