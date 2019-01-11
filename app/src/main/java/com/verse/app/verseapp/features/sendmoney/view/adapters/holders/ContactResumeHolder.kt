package com.verse.app.verseapp.features.sendmoney.view.adapters.holders

import android.support.v7.widget.AppCompatTextView
import android.view.View
import com.verse.app.verseapp.R
import com.verse.app.verseapp.base.list.BaseItemViewHolder
import com.verse.app.verseapp.components.AvatarView
import com.verse.app.verseapp.features.sendmoney.model.entity.Contact

class ContactResumeHolder(itemView: View) :
    BaseItemViewHolder<Contact>(itemView) {


    private lateinit var contact: Contact

    override fun bind(item: Contact) {
        this.contact = item

        val avatarView: AvatarView = itemView.findViewById<AvatarView>(R.id.av_contact_image)
        val nameView: AppCompatTextView = itemView.findViewById<AppCompatTextView>(R.id.tv_contact_name)
        val phoneView: AppCompatTextView = itemView.findViewById<AppCompatTextView>(R.id.tv_contact_phone)
        val ammountView: AppCompatTextView = itemView.findViewById<AppCompatTextView>(R.id.tv_ammount)


        avatarView.setUrl(item.thumbnailValue, item.name)
        nameView.text = item.name
        phoneView.text = item.phone
        ammountView.text = item.ammount?.toString()
    }
}