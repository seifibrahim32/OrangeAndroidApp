package com.example.orangeandroidapp.features.home.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.orangeandroidapp.core.utils.NetWorkResult
import com.example.orangeandroidapp.features.data.service.dao.BookApiResponse
import com.example.orangeandroidapp.features.data.service.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeActivityViewModel @Inject constructor(private val booksRepository: BooksRepository,
                                                application: Application):
    BaseViewModel(application){

    private val _apiResponse: MutableLiveData<NetWorkResult<BookApiResponse>> = MutableLiveData()
    val booksList: MutableLiveData<NetWorkResult<BookApiResponse>> = _apiResponse

    fun getAllBooks(): Job {
        return viewModelScope.launch {
            booksRepository.getAllBooks(context).collect { values ->
                _apiResponse.value = values
            }
        }
    }

    fun getBooksByQuery(query: String): Job {
        return viewModelScope.launch {
            booksRepository.getAllBooksByQuery(context, query).collect { values ->
                _apiResponse.value = values
            }
        }
    }
}