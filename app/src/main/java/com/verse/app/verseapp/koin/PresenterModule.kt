package com.verse.app.verseapp.koin;


import com.verse.app.verseapp.features.sendmoney.presenter.MainPresenter
import org.koin.dsl.module.module


val presenterModuleList = module {

    factory { MainPresenter() }
}