package com.verse.app.verseapp.repository

import com.verse.app.verseapp.repository.models.ApiMarvelCharactersResponse
import io.reactivex.Observable

interface CharactersRepository {

    fun getCharacterList(offset: Int?, count: Int?): Observable<ApiMarvelCharactersResponse>
}