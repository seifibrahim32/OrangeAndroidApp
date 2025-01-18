package com.example.orangeandroidapp.features.book_details.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.bumptech.glide.Glide
import com.example.orangeandroidapp.R
import com.example.orangeandroidapp.databinding.BookDetailsBinding

class BookDetailsFragment : Fragment() {
    private lateinit var booksDetailsMainBinding: BookDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        super.onCreate(savedInstanceState)
        booksDetailsMainBinding = DataBindingUtil.inflate(
            inflater, R.layout.book_details, container,false
        )

        return booksDetailsMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bookTitle = arguments?.getString("title")
        val bookThumbnail = arguments?.getString("thumbnail")
        val bookAuthors = arguments?.getString("authors")
        val bookDescription = arguments?.getString("description")

        booksDetailsMainBinding.toolbar.setNavigationOnClickListener {
            findNavController(this).popBackStack()
        }
        booksDetailsMainBinding.bookTitle.text = bookTitle
        booksDetailsMainBinding.bookAuthors.text = bookAuthors
        booksDetailsMainBinding.bookDescription.text = bookDescription
        Glide.with(booksDetailsMainBinding.root.context)
            .load(bookThumbnail)
            .into(booksDetailsMainBinding.bookImage)

    }
}