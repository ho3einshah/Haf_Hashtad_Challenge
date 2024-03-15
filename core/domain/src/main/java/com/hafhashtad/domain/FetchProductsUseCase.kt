package com.hafhashtad.domain

import com.hafhashtad.data.repository.ProductRepository
import javax.inject.Inject

class FetchProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke() = productRepository.fetchProducts()

}