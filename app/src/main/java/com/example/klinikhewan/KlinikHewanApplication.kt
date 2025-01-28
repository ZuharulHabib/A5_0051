package com.example.klinikhewan

import android.app.Application
import com.example.klinikhewan.di.AppContainer
import com.example.klinikhewan.di.KlinikHewanContainer

class KlinikHewanApplication: Application(){
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = KlinikHewanContainer()
    }
}