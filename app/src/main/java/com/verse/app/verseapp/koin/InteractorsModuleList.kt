package com.verse.app.verseapp.koin


import com.verse.app.verseapp.features.sendmoney.model.interactors.GetAllContactsListOrdered
import com.verse.app.verseapp.features.sendmoney.model.interactors.GetMarvelCharactersListInteractor
import com.verse.app.verseapp.features.sendmoney.model.interactors.GetPhoneContactListInteractor
import com.verse.app.verseapp.features.sendmoney.model.mappers.CharacterListMapper
import com.verse.app.verseapp.features.sendmoney.model.mappers.PhoneListMapper
import org.koin.dsl.module.module

val interactorModuleList = module {

  factory { GetPhoneContactListInteractor(get(), PhoneListMapper()) }
  factory { GetMarvelCharactersListInteractor(get(), CharacterListMapper()) }
  factory { GetAllContactsListOrdered(get(), get()) }

}