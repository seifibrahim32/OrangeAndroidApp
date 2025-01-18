package com.example.orangeandroidapp.features.home.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.orangeandroidapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)
    }
}