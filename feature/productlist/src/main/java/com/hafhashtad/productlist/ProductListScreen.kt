package com.hafhashtad.productlist

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hafhashtad.common.result.Result
import com.hafhashtad.designsystem.component.HafHashtadAppLayoutWithTitle
import com.hafhashtad.designsystem.theme.HafHashtadCodeChallengeTheme
import com.hafhashtad.designsystem.theme.HafHashtadPadding
import com.hafhashtad.model.Product
import com.hafhashtad.ui.FailureOrEmptyContent
import com.hafhashtad.ui.Loading

@Composable
fun ProductListRoute(
    viewModel: ProductListViewModel = hiltViewModel()
) {

    val uiState by viewModel.productListState.collectAsStateWithLifecycle(initialValue = Result.Loading)
    val query by viewModel.query.collectAsStateWithLifecycle()

    ProductListScreen(
        modifier = Modifier
            .padding(HafHashtadPadding.StandardPadding)
            .fillMaxSize(),
        uiState = uiState,
        query = query,
        onQueryChanged = viewModel::onQueryChanged,
        onRetryClicked = viewModel::fetchProducts
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
    uiState: Result<List<Product>>,
    query : String,
    onQueryChanged : (String) -> Unit,
    onRetryClicked: () -> Unit
) {
    HafHashtadAppLayoutWithTitle(title = stringResource(id = R.string.title)) {

        Column {
            when (uiState) {
                is Result.Error -> {
                    FailureOrEmptyContent(message = uiState.errorMsg) {
                        onRetryClicked()
                    }
                }

                Result.Loading -> {
                    Loading()
                }

                is Result.Success -> {
                    ProductListContent(
                        modifier = modifier,
                        productList = uiState.data,
                        query = query,
                        onQueryChanged = {
                            onQueryChanged(it)
                        })
                }

            }

        }

    }


}


@Composable
@Preview(
    name = "light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    apiLevel = 33,
    showBackground = true
)
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    apiLevel = 33,
    showBackground = true
)
private fun ProductListScreenPreview() {
    HafHashtadCodeChallengeTheme {
        ProductListScreen(
            modifier = Modifier.fillMaxSize(),
            query = "780",
            uiState = Result.Loading,
            onQueryChanged = {

            }
        ) {

        }
    }
}