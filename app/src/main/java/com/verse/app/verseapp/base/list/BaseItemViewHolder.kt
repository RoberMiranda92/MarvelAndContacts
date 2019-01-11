package com.verse.app.verseapp.base.list

import android.view.View

abstract class BaseItemViewHolder<M>(itemView: View) : BaseViewHolder(itemView) {


    abstract fun bind(item: M)

    override fun getType(): Int {
        return DATA_ITEM
    }
}