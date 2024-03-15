package com.hafhashtad.productlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.hafhashtad.designsystem.component.SearchBar
import com.hafhashtad.designsystem.icon.HafHashtadCodeChallengeIcons
import com.hafhashtad.designsystem.theme.HafHashtadPadding
import com.hafhashtad.model.Product


@Composable
fun ProductListContent(
    modifier: Modifier = Modifier,
    productList: List<Product>,
    query: String,
    onQueryChanged: (String) -> Unit
) {

    SearchBar(query = query, onQueryChanged = {
        onQueryChanged(it)
    })

    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        items(productList,
            key = {
                it.id.toString()
            }) {

            ProductItem(product = it)

        }
    }

}

@Composable
fun ProductItem(
    product: Product
) {

    val expanded = rememberSaveable {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier
            .padding(vertical = HafHashtadPadding.shortPadding),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 4.dp
    ) {

        Column(
            modifier = Modifier.padding(PaddingValues(HafHashtadPadding.internalWidgetPadding)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product.image)
                        .build(),
                    imageLoader = ImageLoader(LocalContext.current)
                        .newBuilder()
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .networkCachePolicy(CachePolicy.ENABLED)
                        .respectCacheHeaders(false)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = "product image",
                    modifier = Modifier
                        .padding(HafHashtadPadding.StandardPadding)
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    placeholder = painterResource(id = HafHashtadCodeChallengeIcons.imagePlaceholder),
                    error = painterResource(id = HafHashtadCodeChallengeIcons.sadFace)
                )

                Column(
                    modifier = Modifier.weight(2f),
                ) {
                    Text(
                        text = product.title
                            ?: stringResource(id = R.string.item_title_placeholder),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold
                    )


                    Text(
                        text = "$${product.price ?: stringResource(id = R.string.item_price_placeholder)}",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.primary
                    )

                    product.rating?.rate?.let {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = Icons.Rounded.Star, contentDescription = ""
                            )

                            Text(
                                text = "${it}/5",
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )


                        }
                    }

                }

            }

            product.description?.let {
                HorizontalDivider()
                Icon(
                    modifier = Modifier
                        .clickable {
                            expanded.value = !expanded.value
                        }
                        .size(30.dp),
                    imageVector =
                    if (expanded.value) Icons.Rounded.KeyboardArrowUp
                    else Icons.Rounded.KeyboardArrowDown, contentDescription = ""
                )
                AnimatedVisibility(visible = expanded.value && !product.description.isNullOrEmpty()) {
                    Text(
                        text = it,
                    )
                }

            }


        }


    }
}