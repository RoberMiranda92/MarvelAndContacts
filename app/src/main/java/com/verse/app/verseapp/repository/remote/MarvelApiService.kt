package com.verse.app.verseapp.repository.remote

import com.verse.app.verseapp.repository.models.ApiMarvelCharactersResponse
import io.reactivex.Observable
import retrofit2.http.*

interface MarvelApiService {


  @GET("characters")
  fun getCharacterList(@Query("offset") offset: Int?, @Query("limit") limit: Int?): Observable<ApiMarvelCharactersResponse>

}