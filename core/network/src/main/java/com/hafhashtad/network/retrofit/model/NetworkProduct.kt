package com.hafhashtad.network.retrofit.model

import com.hafhashtad.model.Product
import com.hafhashtad.model.Rating
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NetworkProduct(
    @SerialName("id")
    val id: Int?,
    @SerialName("title")
    val title: String?,
    @SerialName("price")
    val price: Double?,
    @SerialName("description")
    val description: String?,
    @SerialName("category")
    val category: String?,
    @SerialName("image")
    val image: String?,
    @SerialName("rating")
    val rating: NetworkProductRating?

)

@Serializable
data class NetworkProductRating(
    @SerialName("rate")
    val rate: Float?,
    @SerialName("count")
    val count: Long?
)


fun NetworkProduct.asExternalModel(): Product {
    return Product(
        id = id,
        title = title,
        category = category,
        price = price,
        description = description,
        image = image,
        rating = Rating(
            rate = rating?.rate,
            count = rating?.count
        )
    )
}
