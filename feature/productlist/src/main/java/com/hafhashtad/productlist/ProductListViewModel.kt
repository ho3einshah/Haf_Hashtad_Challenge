package com.hafhashtad.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hafhashtad.common.result.Result
import com.hafhashtad.common.result.asResult
import com.hafhashtad.domain.FetchProductsUseCase
import com.hafhashtad.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel
@Inject constructor(
    private val fetchProductsUseCase: FetchProductsUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")

    val query: StateFlow<String> = _query

    private val _productListState: MutableStateFlow<Result<List<Product>>> =
        MutableStateFlow(Result.Loading)

    val productListState =
        combine(_productListState, _query) { productListState, query ->
            if (productListState !is Result.Success) {
                productListState
            } else {
                val filteredList = productListState.data.filter {
                    it.title?.contains(query, ignoreCase = true) == true
                }
                Result.Success(filteredList)
            }
        }

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            fetchProductsUseCase().asResult().collect {
                _productListState.emit(it)
            }
        }

    }

    fun onQueryChanged(query: String) {
        _query.value = query
    }

}