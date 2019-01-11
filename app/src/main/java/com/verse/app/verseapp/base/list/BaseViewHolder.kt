package com.verse.app.verseapp.base.list

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    companion object {
        const val LOADING_ITEM = 0
        const val DATA_ITEM = 1

    }

    abstract fun getType(): Int
}