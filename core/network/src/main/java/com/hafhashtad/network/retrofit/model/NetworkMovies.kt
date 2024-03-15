package com.hafhashtad.network.retrofit.model

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
    val rating: NetworkRating?

)

@Serializable
data class NetworkRating(
    @SerialName("rate")
    val rate: Float?,
    @SerialName("count")
    val count: Long?
)


//fun NetworkMovie.asExternalModel(): Movie {
//    return Movie(
//        id = id,
//        adult = adult,
//        imageSubPath = imageSubPath,
//        title = title
//    )
//}
