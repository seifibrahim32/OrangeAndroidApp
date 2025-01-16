package com.example.orangeandroidapp.features.home.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orangeandroidapp.features.data.service.dao.Item
import com.example.orangeandroidapp.databinding.ItemCardBinding

class BooksAdapter() : RecyclerView.Adapter<BooksAdapter.DataViewHolder>() {

    private lateinit var binding: ItemCardBinding
    var booksList = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksAdapter.DataViewHolder {
        binding  = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent,
            false
        )
        return DataViewHolder(binding)
    }

    inner class DataViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            try {
                binding.item = item.volumeInfo
                binding.executePendingBindings()
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }


    override fun onBindViewHolder(holder: BooksAdapter.DataViewHolder, position: Int) {
        val model: Item = booksList[position]
//        holder.bookTitle.text = model.volumeInfo!!.title
//        holder.bookAuthor.text = model.volumeInfo!!.authors[0]

//        Glide.with(holder.itemView)
//            .load(model.volumeInfo!!.imageLinks!!.smallThumbnail)
//            .into(holder.)
        holder.bind(model)
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
