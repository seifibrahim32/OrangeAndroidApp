package com.example.orangeandroidapp.features.data.service.dao

import com.google.gson.annotations.SerializedName

data class Pdf (
  @SerializedName("isAvailable") var isAvailable : Boolean? = null
)