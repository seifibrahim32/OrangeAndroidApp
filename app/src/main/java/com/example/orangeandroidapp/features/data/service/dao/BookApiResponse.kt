package com.example.orangeandroidapp.features.data.service.dao

import com.google.gson.annotations.SerializedName

data class BookApiResponse(
    @SerializedName("kind") var kind: String? = null,
    @SerializedName("totalItems") var totalItems: Int?= null,
    @SerializedName("items") var items: List<Item>? = null
)