package com.hafhashtad.productlist

import androidx.lifecycle.viewModelScope
import com.hafhashtad.common.result.Result
import com.hafhashtad.domain.FetchProductsUseCase
import com.hafhashtad.model.Product
import com.hafhashtad.model.Rating
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProductListViewModelTest {

    val productsUseCase: FetchProductsUseCase = mockk(relaxed = true)

    private lateinit var viewModel: ProductListViewModel

    private val input = listOf(
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


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init()
        val dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)
        viewModel = ProductListViewModel(
            fetchProductsUseCase = productsUseCase,)
    }

    @Test
    fun `check first ui state`() {
        runTest {


            Assert.assertEquals(
                Result.Loading,
                viewModel.productListState.stateIn(viewModel.viewModelScope).value
            )
        }
    }

    @Test
    fun `ui state after success service Call returns list of product`() {
        runTest {
            every {
                productsUseCase()
            } answers {
                flowOf(input)
            }
            viewModel.fetchProducts()
            assert(viewModel.productListState.stateIn(viewModel.viewModelScope).value is Result.Success)

        }
    }

    @Test
    fun `ui state after exception on domain is Error`() {
        runTest {
            every {
                productsUseCase()
            } answers {
                flow {
                    throw Exception()
                }
            }
            viewModel.fetchProducts()
            assert(viewModel.productListState.stateIn(viewModel.viewModelScope).value is Result.Error)

        }
    }

    @Test
    fun `filtered Data only contains query`() {
        runTest {
            every {
                productsUseCase()
            } answers {
                flowOf(input)
            }
            viewModel.fetchProducts()
            viewModel.onQueryChanged("h")
            assert(
                (viewModel.productListState.stateIn(viewModel.viewModelScope).value as
                        Result.Success).data.isEmpty()
            )

        }

    }

    @Test
    fun `filtered Data holds contains query`() {
        runTest {
            every {
                productsUseCase()
            } answers {
                flowOf(input)
            }
            viewModel.fetchProducts()
            viewModel.onQueryChanged("title")
            assert(
                (viewModel.productListState.stateIn(viewModel.viewModelScope).value as
                        Result.Success).data.isNotEmpty()
            )

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