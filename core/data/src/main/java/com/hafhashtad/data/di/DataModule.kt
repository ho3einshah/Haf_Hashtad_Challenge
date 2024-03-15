package com.hafhashtad.data.di

import com.hafhashtad.data.repository.ProductRepository
import com.hafhashtad.data.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {

    @Binds
    fun bindsProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}