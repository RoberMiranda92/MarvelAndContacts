package com.verse.app.verseapp.repository.local

import android.app.Application
import com.google.gson.Gson
import com.verse.app.verseapp.repository.CharactersRepository
import com.verse.app.verseapp.repository.models.ApiMarvelCharactersResponse
import io.reactivex.Observable

class CharactersLocalRepository(val application: Application) : CharactersRepository {


    override fun getCharacterList(offset: Int?, count: Int?): Observable<ApiMarvelCharactersResponse> {


        return Observable.create<ApiMarvelCharactersResponse> { emitter ->
            try {
                val file: String = AndroidJsonReader.readJsonFile(application, "characterList.json")

                emitter.onNext(Gson().fromJson(file, ApiMarvelCharactersResponse::class.java))
                emitter.onComplete()
            } catch (ex: Exception) {
                emitter.onError(ex)
            }

        }
    }
}