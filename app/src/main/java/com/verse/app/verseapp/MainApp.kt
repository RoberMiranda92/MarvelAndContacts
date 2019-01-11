package com.verse.app.verseapp

import android.app.Application
import com.verse.app.verseapp.koin.interactorModuleList
import com.verse.app.verseapp.koin.presenterModuleList
import com.verse.app.verseapp.koin.repositoryModuleList
import org.koin.android.ext.android.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin(this, listOf(presenterModuleList, interactorModuleList, repositoryModuleList))
    }
}