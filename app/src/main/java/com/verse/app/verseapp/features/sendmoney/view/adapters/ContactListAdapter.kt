package com.verse.app.verseapp.features.sendmoney.view.adapters

import android.view.View
import com.verse.app.verseapp.R
import com.verse.app.verseapp.base.list.BaseListAdapter
import com.verse.app.verseapp.features.sendmoney.model.entity.Contact
import com.verse.app.verseapp.features.sendmoney.view.adapters.holders.ContactLoaderViewHolder
import com.verse.app.verseapp.features.sendmoney.view.adapters.holders.ContactViewHolder

class ContactListAdapter(override var listener: ContactListAdapterListener?) :
    BaseListAdapter<Contact, ContactViewHolder, ContactLoaderViewHolder, ContactListAdapter.ContactListAdapterListener>(
        listener
    ),
    ContactViewHolder.OnContactSelecterListener {


    override fun getViewLayout(): Int = R.layout.item_contact

    override fun getLoadingView(): Int = R.layout.item_contact_loader

    override fun createItemViewHolder(view: View): ContactViewHolder =
        ContactViewHolder(view, this)


    override fun createLoadingViewHolder(view: View):
            ContactLoaderViewHolder = ContactLoaderViewHolder(view)


    override fun onContactSelected(selected: Boolean, contact: Contact) {
        if (selected)
            listener?.onElementSelected(contact)
        else
            listener?.onElementUnSelected(contact)

    }

    interface ContactListAdapterListener : BaseListAdapter.BaseAdapterListener<Contact> {

        fun onElementSelected(contact: Contact)

        fun onElementUnSelected(contact: Contact)

    }
}