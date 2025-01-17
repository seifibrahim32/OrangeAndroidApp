package com.example.orangeandroidapp.features.data.service.remote

import com.example.orangeandroidapp.features.data.service.api.BooksService
import com.example.orangeandroidapp.features.data.service.dao.BookApiResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: BooksService) {
    suspend fun getAllBooks(): Response<BookApiResponse>
            = apiService.getAllBooks()
    suspend fun getBooksByQuery(query: String): Response<BookApiResponse>
            = apiService.getBooksByQuery(query)
}