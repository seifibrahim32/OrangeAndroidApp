
package com.example.orangeandroidapp.features.home.ui.fragments
import android.view.Menu
import android.view.MenuInflater
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.orangeandroidapp.R
import com.example.orangeandroidapp.databinding.HomeScreenBinding
import com.example.orangeandroidapp.features.data.service.dao.BookApiResponse
import com.example.orangeandroidapp.features.data.service.handler.ApiResultHandler
import com.example.orangeandroidapp.features.home.ui.adapter.BooksAdapter
import com.example.orangeandroidapp.features.home.ui.viewmodels.BooksListsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksListsFragment : Fragment(), SearchView.OnQueryTextListener {

    private val homeViewModel by viewModels<BooksListsViewModel>()

    private lateinit var booksAdapter: BooksAdapter

    private lateinit var homeBinding: HomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = HomeScreenBinding.inflate(inflater)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        prepareRecyclerView()
        getAllBooks()
        observeBooksData(view)
    }

    private fun prepareRecyclerView(){
        booksAdapter = BooksAdapter{ selectedBook ->
            val bundle = bundleOf(
                "title" to selectedBook.title.orEmpty(),
                "thumbnail" to selectedBook.imageLinks!!.thumbnail.orEmpty(),
                "authors" to selectedBook.authors!!.joinToString(", "),
                "description" to selectedBook.description.orEmpty()
            )
            findNavController(this).navigate(R.id.action_homeActivity_to_book_details,
                bundle)
        }
        homeBinding.recyclerView.apply { adapter = booksAdapter }
        homeBinding.swipeRefreshLayout.setOnRefreshListener {
            homeViewModel.getAllBooks()
        }
    }

    private fun getAllBooks(){
        homeViewModel.getAllBooks()
    }

    private fun observeBooksData(view: View) {

        try {
            homeViewModel.booksList.observe(viewLifecycleOwner) { response ->
                val apiResultHandler = ApiResultHandler<BookApiResponse>(view.context,
                    onLoading = {
                        homeBinding.progressBar.visibility = View.VISIBLE
                        homeBinding.recyclerView.visibility = View.GONE
                    },
                    onSuccess = { data ->
                        homeBinding.progressBar.visibility = View.GONE

                        data?.items.let {
                            if (it != null) {
                                booksAdapter.setBooksItems(it)
                            }
                        }
                        homeBinding.recyclerView.visibility = View.VISIBLE
                        homeBinding.swipeRefreshLayout.isRefreshing = false
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(submittedQuery: String): Boolean {
        if (submittedQuery.isNotEmpty()) {
            homeBinding.progressBar.visibility = View.VISIBLE
            homeViewModel.getBooksByQuery(submittedQuery)
        }
        return true
    }


    override fun onQueryTextChange(query: String): Boolean {
        if (query.isNotEmpty()) {
            homeBinding.progressBar.visibility = View.VISIBLE
            homeViewModel.getBooksByQuery(query)
        }
        return true
    }

}