package com.verse.app.verseapp.repository

import com.verse.app.verseapp.repository.models.PhoneContact
import io.reactivex.Observable

interface ContactsRepository {

    fun getContactList(): Observable<List<PhoneContact>>
}