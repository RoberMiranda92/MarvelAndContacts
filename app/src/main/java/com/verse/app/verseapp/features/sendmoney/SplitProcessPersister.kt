package com.verse.app.verseapp.features.sendmoney.view

import com.verse.app.verseapp.features.sendmoney.model.entity.Contact


class SplitProcessPersister {

    private object Holder {
        val INSTANCE = SplitProcessPersister()
    }

    companion object {
        val instance: SplitProcessPersister by lazy { Holder.INSTANCE }
    }

    var contactList: ArrayList<Contact> = ArrayList()
    var ammout: Double = 0.00

    fun addContact(contact: Contact) {
        contactList.add(contact)
    }

    fun removeContact(contact: Contact) {
        contactList.remove(contact)
    }

    fun clear() {
        this.ammout = 0.00
        this.contactList.clear()
    }

}


