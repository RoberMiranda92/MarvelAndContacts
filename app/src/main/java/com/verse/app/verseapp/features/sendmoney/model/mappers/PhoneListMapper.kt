package com.verse.app.verseapp.features.sendmoney.model.mappers

import com.verse.app.verseapp.features.sendmoney.model.entity.Contact
import com.verse.app.verseapp.features.sendmoney.model.entity.ContactList
import com.verse.app.verseapp.repository.Mapper
import com.verse.app.verseapp.repository.models.PhoneContact

class PhoneListMapper : Mapper<ContactList, List<PhoneContact>> {


    override fun fromApi(apiModel: List<PhoneContact>): ContactList {
        val builder: ContactList.Builder = ContactList.Builder()

        for (contact: PhoneContact in apiModel) {
            val contactBuilder: Contact.Builder = Contact.Builder()
            builder.addContact(
                contactBuilder.setContactName(contact.name)
                    .setContactPhone(contact.phone).setContactThumbnail(contact.thumbnailValue).build()
            )

        }
        return builder.build()
    }

    override fun toApi(model: ContactList): List<PhoneContact> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}