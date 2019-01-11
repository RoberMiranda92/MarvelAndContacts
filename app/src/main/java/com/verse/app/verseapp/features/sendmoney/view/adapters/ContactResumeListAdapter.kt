package com.verse.app.verseapp.features.sendmoney.view.adapters

import android.view.View
import com.verse.app.verseapp.R
import com.verse.app.verseapp.base.list.BaseListAdapter
import com.verse.app.verseapp.features.sendmoney.model.entity.Contact
import com.verse.app.verseapp.features.sendmoney.view.adapters.holders.ContactLoaderViewHolder
import com.verse.app.verseapp.features.sendmoney.view.adapters.holders.ContactResumeHolder

class ContactResumeListAdapter :
    BaseListAdapter<Contact, ContactResumeHolder,
            ContactLoaderViewHolder,BaseListAdapter.BaseAdapterListener<Contact>>(
        null
    ){


    override fun getViewLayout(): Int = R.layout.item_contact_resume

    override fun getLoadingView(): Int = R.layout.item_contact_loader

    override fun createItemViewHolder(view: View): ContactResumeHolder =
        ContactResumeHolder(view)


    override fun createLoadingViewHolder(view: View):
            ContactLoaderViewHolder = ContactLoaderViewHolder(view)

}