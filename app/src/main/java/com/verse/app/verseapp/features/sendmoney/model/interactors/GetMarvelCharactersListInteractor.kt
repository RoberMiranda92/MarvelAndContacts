package com.verse.app.verseapp.features.sendmoney.model.interactors

import com.verse.app.verseapp.features.sendmoney.model.entity.ContactList
import com.verse.app.verseapp.features.sendmoney.model.mappers.CharacterListMapper
import com.verse.app.verseapp.repository.CharactersRepository
import io.reactivex.Observable

class GetMarvelCharactersListInteractor(private var repository: CharactersRepository, private var mapper: CharacterListMapper) {

  fun getCharacters(offset: Int, count: Int): Observable<ContactList> {
    return repository.getCharacterList(offset, count).map { mapper.fromApi(it) }
  }
}