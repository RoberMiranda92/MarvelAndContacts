package com.verse.app.verseapp.features.sendmoney.view.adapters.holders

import android.support.v7.widget.AppCompatCheckBox
import android.support.v7.widget.AppCompatTextView
import android.view.View
import com.verse.app.verseapp.R
import com.verse.app.verseapp.base.list.BaseItemViewHolder
import com.verse.app.verseapp.components.AvatarView
import com.verse.app.verseapp.features.sendmoney.model.entity.Contact

class ContactViewHolder(itemView: View, var listener: OnContactSelecterListener) :
    BaseItemViewHolder<Contact>(itemView) {


    private lateinit var contact: Contact

    override fun bind(item: Contact) {
        this.contact = item

        val avatarView: AvatarView = itemView.findViewById<AvatarView>(R.id.av_contact_image)
        val nameView: AppCompatTextView = itemView.findViewById<AppCompatTextView>(R.id.tv_contact_name)
        val phoneView: AppCompatTextView = itemView.findViewById<AppCompatTextView>(R.id.tv_contact_phone)
        val checkView: AppCompatCheckBox = itemView.findViewById<AppCompatCheckBox>(R.id.cb_selected)


        avatarView.setUrl(item.thumbnailValue, item.name)
        nameView.text = item.name
        phoneView.text = item.phone
        checkView.setOnCheckedChangeListener { _, selected ->
            listener.onContactSelected(selected, this.contact)
        }
    }

    interface OnContactSelecterListener {

        fun onContactSelected(selected: Boolean, contact: Contact)
    }
}