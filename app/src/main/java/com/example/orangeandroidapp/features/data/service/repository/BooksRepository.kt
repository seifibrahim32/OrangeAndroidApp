package com.example.orangeandroidapp.features.data.service.repository

import android.content.Context
import com.example.orangeandroidapp.core.utils.NetWorkResult
import com.example.orangeandroidapp.core.utils.toResultFlow
import com.example.orangeandroidapp.features.data.service.dao.BookApiResponse
import com.example.orangeandroidapp.features.data.service.dao.Item
import com.example.orangeandroidapp.features.data.service.remote.RemoteDataSource
import com.google.gson.JsonObject
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@ActivityRetainedScoped
class BooksRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
){
    suspend fun getAllBooks(context: Context): Flow<NetWorkResult<BookApiResponse>> {
        return toResultFlow(context){
            remoteDataSource.getAllBooks()
        }
    }

    suspend fun getAllBooksByQuery(context: Context, query: String): Flow<NetWorkResult<BookApiResponse>> {
        return toResultFlow(context){
            remoteDataSource.getBooksByQuery(query)
        }
    }
}