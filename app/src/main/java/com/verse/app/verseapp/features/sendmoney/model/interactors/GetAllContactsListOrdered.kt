package com.verse.app.verseapp.features.sendmoney.model.interactors

import com.verse.app.verseapp.features.sendmoney.model.entity.Contact
import com.verse.app.verseapp.features.sendmoney.model.entity.ContactList
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function

class GetAllContactsListOrdered(
    private val getMarvelCharactersListInteractor: GetMarvelCharactersListInteractor,
    private val getPhoneContactListInteractor: GetPhoneContactListInteractor
) {


    fun getAllContactsListOrdered(): Observable<List<Contact>> {
        val combined = Observable.zip(getMarvelCharactersListInteractor
            .getCharacters(0, 50).onErrorReturn(object : Function<Throwable, ContactList> {

                override fun apply(t: Throwable?): ContactList {
                    return ContactList()
                }

            })
            , getPhoneContactListInteractor.getContacts().onErrorReturn(object : Function<Throwable, ContactList> {

                override fun apply(t: Throwable?): ContactList {
                    return ContactList()
                }

            }),
            BiFunction<ContactList, ContactList, List<Contact>> { t1, t2 ->
                val contactList:ArrayList<Contact> = ArrayList()

                contactList.addAll(t1.contacts)
                contactList.addAll(t2.contacts)

                contactList.sortedWith(compareBy({ it.name }))
            })

        return combined
    }
}