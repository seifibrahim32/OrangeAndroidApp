package com.example.orangeandroidapp.features.data.service.api

import com.example.orangeandroidapp.features.data.service.dao.BookApiResponse
import com.example.orangeandroidapp.core.utils.AppConstants
import retrofit2.Response
import retrofit2.http.Query
import retrofit2.http.GET

interface BooksService{
    @GET(AppConstants.VOLUMES_ENDPOINT)
    suspend fun getAllBooks(@Query("q") q:String="all",
                         @Query("key") apiKey: String = AppConstants.API_KEY) : Response<BookApiResponse>
    @GET(AppConstants.VOLUMES_ENDPOINT)
    suspend fun getBooksByQuery(@Query("q") q:String,
                         @Query("key") apiKey: String = AppConstants.API_KEY) : Response<BookApiResponse>
}