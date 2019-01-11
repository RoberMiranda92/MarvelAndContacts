package com.verse.app.verseapp.features.sendmoney.model.interactors

import com.verse.app.verseapp.features.sendmoney.model.entity.ContactList
import com.verse.app.verseapp.features.sendmoney.model.mappers.PhoneListMapper
import com.verse.app.verseapp.repository.ContactsRepository
import io.reactivex.Observable


class GetPhoneContactListInteractor(private var repository: ContactsRepository, private var mapper: PhoneListMapper) {

    fun getContacts(): Observable<ContactList> {
        return repository.getContactList().map { mapper.fromApi(it) }
    }
}