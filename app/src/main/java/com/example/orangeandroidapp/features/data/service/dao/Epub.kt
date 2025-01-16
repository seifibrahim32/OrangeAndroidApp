package com.example.orangeandroidapp.features.data.service.dao

import com.google.gson.annotations.SerializedName


data class Epub (

  @SerializedName("isAvailable"  ) var isAvailable  : Boolean? = null,
  @SerializedName("downloadLink" ) var downloadLink : String?  = null

)