package com.verse.app.verseapp.features.sendmoney.model.entity


open class ContactList {

    var contacts: ArrayList<Contact>
        private set

    constructor(builder: Builder) : this() {
        this.contacts = builder.contacts
    }

    constructor() {
        contacts = ArrayList()
    }


    class Builder {

        var contacts: ArrayList<Contact> = ArrayList()
            private set

        fun addContact(contact: Contact) =
            apply { contacts.add(contact) }


        fun build(): ContactList =
            ContactList(this)


    }
}