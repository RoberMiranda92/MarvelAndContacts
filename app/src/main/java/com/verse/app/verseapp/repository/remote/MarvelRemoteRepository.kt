package com.verse.app.verseapp.repository.remote

import com.verse.app.verseapp.repository.CharactersRepository
import com.verse.app.verseapp.repository.models.ApiMarvelCharactersResponse
import io.reactivex.Observable

class MarvelRemoteRepository : CharactersRepository {

  private val marvelApi: MarvelApiService = MarvelApiFactory.create()

  override fun getCharacterList(offset: Int?, count: Int?): Observable<ApiMarvelCharactersResponse> = marvelApi.getCharacterList(offset, count)

}