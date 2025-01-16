package com.example.orangeandroidapp.features.home.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OrangeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}