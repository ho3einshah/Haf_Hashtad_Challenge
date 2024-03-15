package com.hafhashtad.network.di

import com.hafhashtad.network.BuildConfig
import com.hafhashtad.network.retrofit.api.Constants
import com.hafhashtad.network.retrofit.api.NetworkApi
import com.hafhashtad.network.retrofit.datasource.NetworkDataSource
import com.hafhashtad.network.retrofit.datasource.NetworkDataSourceImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {


    companion object {
        @Provides
        @Singleton
        fun providesNetworkJson(): Json =
            Json {
                ignoreUnknownKeys = true
            }

        @Provides
        @Singleton
        fun providesRetrofitNetworkDataSource(
            networkJson: Json,
        ): NetworkApi {
            val baseUrl = Constants.BASE_URL

            val okHttpClientBuilder =
                OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                okHttpClientBuilder.addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    },
                )
            }

            val builder =
                Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClientBuilder.build())
                    .addConverterFactory(
                        networkJson.asConverterFactory("application/json".toMediaType()),
                    )
                    .build()

            return builder.create(NetworkApi::class.java)
        }
    }

    @Binds
    fun bindsNetworkDatasource(networkDataSource: NetworkDataSourceImpl): NetworkDataSource
}