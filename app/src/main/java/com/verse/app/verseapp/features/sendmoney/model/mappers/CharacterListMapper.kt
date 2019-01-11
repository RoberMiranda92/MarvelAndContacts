package com.verse.app.verseapp.features.sendmoney.model.mappers

import com.verse.app.verseapp.features.sendmoney.model.entity.Contact
import com.verse.app.verseapp.features.sendmoney.model.entity.ContactList
import com.verse.app.verseapp.repository.Mapper
import com.verse.app.verseapp.repository.models.ApiMarvelCharactersResponse
import com.verse.app.verseapp.repository.models.ResultsItem
import com.verse.app.verseapp.repository.models.getFullPath

class CharacterListMapper : Mapper<ContactList, ApiMarvelCharactersResponse> {

    override fun fromApi(apiModel: ApiMarvelCharactersResponse): ContactList {

        val builder: ContactList.Builder = ContactList.Builder()

        for (character: ResultsItem in apiModel.data.results) {
            val contactBuilder: Contact.Builder = Contact.Builder()
            builder.addContact(contactBuilder.setContactName(character.name!!).setContactThumbnail(character.thumbnail!!.getFullPath()).build())

        }
        return builder.build()
    }

    override fun toApi(model: ContactList): ApiMarvelCharactersResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}