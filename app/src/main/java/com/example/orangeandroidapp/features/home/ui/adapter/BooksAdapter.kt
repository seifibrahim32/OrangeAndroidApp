package com.example.orangeandroidapp.features.home.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orangeandroidapp.R
import com.example.orangeandroidapp.features.data.service.dao.Item
import com.example.orangeandroidapp.databinding.ItemCardBinding
import com.example.orangeandroidapp.features.data.service.dao.VolumeInfo

class BooksAdapter(private val onBookClick: (VolumeInfo) -> Unit) : RecyclerView.Adapter<BooksAdapter.DataViewHolder>() {

    private lateinit var cardBinding: ItemCardBinding
    private var booksList = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksAdapter.DataViewHolder {
        cardBinding  = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent,
            false
        )
        return DataViewHolder(cardBinding)
    }

    inner class DataViewHolder(cardBinding: ItemCardBinding) :
        RecyclerView.ViewHolder(cardBinding.root) {

        fun bind(bookInfo: VolumeInfo){

            cardBinding.apply {
                cardBinding.item = bookInfo
                cardBinding.bookAuthor.text = bookInfo.authors?.joinToString(", ")

                Glide.with(root.context)
                    .load(bookInfo.imageLinks?.smallThumbnail)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(bookImage)
            }
        }
    }


    override fun onBindViewHolder(holder: BooksAdapter.DataViewHolder, position: Int) {
        val model: Item = booksList[position]
        holder.bind(model.volumeInfo!!)

        holder.itemView.setOnClickListener {
            onBookClick(model.volumeInfo!!)
        }
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
