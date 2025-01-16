package com.example.orangeandroidapp.features.book_details.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.orangeandroidapp.R
import dagger.hilt.android.AndroidEntryPoint

class BookDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding: ViewDataBinding? = DataBindingUtil.setContentView(
            this, R.layout.book_details
        )
    }
}