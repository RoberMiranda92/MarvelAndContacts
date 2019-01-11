package com.verse.app.verseapp.features.sendmoney.model.mappers

import com.verse.app.verseapp.features.sendmoney.model.entity.Contact
import com.verse.app.verseapp.repository.Mapper
import com.verse.app.verseapp.repository.models.ApiMarvelCharacter

class CharacterMapper : Mapper<Contact, ApiMarvelCharacter> {

    override fun fromApi(apiModel: ApiMarvelCharacter): Contact {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toApi(model: Contact): ApiMarvelCharacter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}