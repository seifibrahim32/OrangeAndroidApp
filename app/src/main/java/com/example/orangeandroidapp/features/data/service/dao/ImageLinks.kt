package com.example.orangeandroidapp.features.data.service.dao

import com.google.gson.annotations.SerializedName

data class ImageLinks (

  @SerializedName("smallThumbnail" ) var smallThumbnail : String? = null,
  @SerializedName("thumbnail"      ) var thumbnail      : String? = null

)