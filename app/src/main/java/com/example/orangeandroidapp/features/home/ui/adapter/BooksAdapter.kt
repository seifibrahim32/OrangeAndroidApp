package com.example.orangeandroidapp.features.home.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orangeandroidapp.R
import com.example.orangeandroidapp.features.data.service.dao.Item
import com.example.orangeandroidapp.databinding.ItemCardBinding
import com.example.orangeandroidapp.features.data.service.dao.VolumeInfo

class BooksAdapter() : RecyclerView.Adapter<BooksAdapter.DataViewHolder>() {

    private lateinit var binding: ItemCardBinding
    var booksList = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksAdapter.DataViewHolder {
        binding  = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent,
            false
        )
        return DataViewHolder(binding)
    }

    inner class DataViewHolder(binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val bookTitle: TextView = itemView.findViewById(R.id.book_title)
        private val bookAuthor: TextView = itemView.findViewById(R.id.book_author)
        private val bookImage: ImageView = itemView.findViewById(R.id.bookImage)
        private val book_description: TextView = itemView.findViewById(R.id.book_description)

        fun bind(book: VolumeInfo) {
            bookTitle.text = book.title
            bookAuthor.text = book.authors?.get(0)
            book_description.text = book.description

            Glide.with(itemView.context)
                .load(book.imageLinks!!.smallThumbnail)
                .placeholder(R.drawable.ic_launcher_background)
                .into(bookImage)
        }
    }


    override fun onBindViewHolder(holder: BooksAdapter.DataViewHolder, position: Int) {
        val model: Item = booksList[position]
        holder.bind(model.volumeInfo!!)
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBooksItems(data: List<Item>) {
        try {
            booksList.clear()
            if (data.isNotEmpty()) {
                booksList.addAll(data)
            }
            notifyDataSetChanged()
        } catch (e: Exception) {
            e.stackTrace
        }
    }
}
