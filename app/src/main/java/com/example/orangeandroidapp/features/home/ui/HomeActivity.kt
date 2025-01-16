package com.example.orangeandroidapp.features.home.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orangeandroidapp.R
import com.example.orangeandroidapp.databinding.HomeScreenBinding
import com.example.orangeandroidapp.features.data.service.dao.BookApiResponse
import com.example.orangeandroidapp.features.data.service.handler.ApiResultHandler
import com.example.orangeandroidapp.features.home.ui.adapter.BooksAdapter
import com.example.orangeandroidapp.features.home.ui.viewmodels.HomeActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() , SearchView.OnQueryTextListener {

    private val homeViewModel by viewModels<HomeActivityViewModel>()

    lateinit var booksAdapter: BooksAdapter

    lateinit var homeBinding: HomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        homeBinding = DataBindingUtil.setContentView(this@HomeActivity, R.layout.home_screen)

        prepareRecyclerView()
        getBooks()
        observeBooksData()
    }

    private fun prepareRecyclerView(){
        booksAdapter = BooksAdapter()
        homeBinding.recyclerView.apply { adapter = booksAdapter }
    }

    private fun getBooks(){
        homeViewModel.getBooksList()
    }

    private fun observeBooksData() {

        try {
            homeViewModel.booksList.observe(this) { response ->
                val apiResultHandler = ApiResultHandler<BookApiResponse>(this@HomeActivity,
                    onLoading = {
                        homeBinding.progressBar.visibility = View.VISIBLE
                    },
                    onSuccess = { data ->
                        homeBinding.progressBar.visibility = View.GONE
                        Log.v("RetroFit debug", "j")
//                        data!!.items.let {
//                            booksAdapter.setBooksItems(it)
//                        }
                    },
                    onFailure = {
                        homeBinding.progressBar.visibility = View.VISIBLE
                    })
                apiResultHandler.handleApiResult(response)
            }
        } catch (e: Exception) {
            e.stackTrace
        }
    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.app_bar_search -> {
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

}