package com.verse.app.verseapp.koin

import android.app.Application
import com.verse.app.verseapp.BuildConfig
import com.verse.app.verseapp.repository.CharactersRepository
import com.verse.app.verseapp.repository.ContactsRepository
import com.verse.app.verseapp.repository.local.CharactersLocalRepository
import com.verse.app.verseapp.repository.local.ContactsLocalRepository
import com.verse.app.verseapp.repository.remote.MarvelRemoteRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module


val repositoryModuleList = module {

    single<CharactersRepository> { getRepositorySource(androidApplication()) }

    single<ContactsRepository> { ContactsLocalRepository(androidApplication()) }


}

fun getRepositorySource(application: Application): CharactersRepository =

    when (BuildConfig.BUILD_TYPE) {
        "debug", "release" -> {
            MarvelRemoteRepository()
        }
        else -> {
            CharactersLocalRepository(application)
        }

    }
