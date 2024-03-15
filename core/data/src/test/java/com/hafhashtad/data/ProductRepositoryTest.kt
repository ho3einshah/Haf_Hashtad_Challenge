package com.hafhashtad.data

import com.hafhashtad.data.repository.ProductRepositoryImpl
import com.hafhashtad.model.Product
import com.hafhashtad.model.Rating
import com.hafhashtad.network.retrofit.datasource.NetworkDataSource
import com.hafhashtad.network.retrofit.model.NetworkProduct
import com.hafhashtad.network.retrofit.model.NetworkProductRating
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProductRepositoryTest {


    val networkDataSource: NetworkDataSource = mockk()

    lateinit var repository: ProductRepositoryImpl

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init()
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
        repository = ProductRepositoryImpl(
            remoteDataSource = networkDataSource,
            dispatcher
        )
    }

    @Test
    fun `check success remote call returns data`() {
        runTest {
            coEvery {
                networkDataSource.fetchProducts()
            } returns listOf(
                NetworkProduct(
                    id = 1,
                    title = "title",
                    price = 22.2,
                    description = "desc",
                    category = "category",
                    image = "sample url",
                    rating = NetworkProductRating(
                        rate = 4.4f,
                        count = 120
                    )
                )
            )
            val mappedData = listOf(
                Product(
                    id = 1,
                    title = "title",
                    price = 22.2,
                    description = "desc",
                    category = "category",
                    image = "sample url",
                    rating = Rating(
                        rate = 4.4f,
                        count = 120
                    )

                )
            )

            repository.fetchProducts().collect {
                Assert.assertEquals(mappedData, it)
            }


        }

    }

    @Test
    fun `check does not emit data on exception`() {
        runTest {
            coEvery {
                networkDataSource.fetchProducts()
            } throws Exception("Exception")
            var emittedDataCount = 0
            try {
                emittedDataCount = repository.fetchProducts().toList().size

            } catch (_: Exception) {
            }
            Assert.assertEquals(0, emittedDataCount)


        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        clearAllMocks()
        unmockkAll()
        Dispatchers.resetMain()
    }
}