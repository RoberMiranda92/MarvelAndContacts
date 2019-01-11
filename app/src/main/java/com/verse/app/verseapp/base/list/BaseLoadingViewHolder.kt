package com.verse.app.verseapp.base.list

import android.view.View
import com.verse.app.verseapp.base.list.BaseViewHolder

abstract class BaseLoadingViewHolder(itemView: View) : BaseViewHolder(itemView) {


    abstract fun bind()

    override fun getType(): Int {
        return LOADING_ITEM
    }


}