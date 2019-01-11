package com.verse.app.verseapp.base.list


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


abstract class BaseListAdapter<M, H : BaseViewHolder, L :
BaseViewHolder, T : BaseListAdapter.BaseAdapterListener<M>?>(protected open var listener: T?) :
    RecyclerView.Adapter<BaseViewHolder>() {

    var data: MutableList<M>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var moreData: Boolean = false

    init {
        data = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        when (viewType) {
            BaseViewHolder.DATA_ITEM -> {
                val view = LayoutInflater.from(parent.context).inflate(getViewLayout(), parent, false)
                return createItemViewHolder(view)
            }
            BaseViewHolder.LOADING_ITEM -> {
                val view = LayoutInflater.from(parent.context).inflate(getLoadingView(), parent, false)
                return createLoadingViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(getViewLayout(), parent, false)
                return createItemViewHolder(view)

            }

        }

    }


    protected abstract fun getViewLayout(): Int

    protected abstract fun getLoadingView(): Int

    protected abstract fun createItemViewHolder(view: View): H

    protected abstract fun createLoadingViewHolder(view: View): L


    override fun getItemCount(): Int {
        return if (moreData) data!!.size + 1 else data!!.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        when (holder.getType()) {
            BaseViewHolder.DATA_ITEM -> {
                (holder as BaseItemViewHolder<M>).bind(data!!.get(position)!!)
                holder.itemView.setOnClickListener {
                    listener?.onClickItem(data!!.get(position)!!, position)
                }
            }
            BaseViewHolder.LOADING_ITEM -> {
                (holder as BaseLoadingViewHolder).bind()
                listener?.onLoadingShow()
            }
        }

    }

    override fun getItemViewType(position: Int): Int {

        return if (getItemCount() - 1 == position && moreData) BaseViewHolder.LOADING_ITEM
        else return BaseViewHolder.DATA_ITEM

    }

    fun hasElements(): Boolean {
        return data!!.size > 0
    }

    fun removeElement(position: Int) {
        if (position >= 0 && position < data!!.size) {
            data!!.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount - position)
        }
    }

    fun removeElement(element: M) {
        if (data!!.contains(element)) {
            this.removeElement(data!!.indexOf(element))
        }
    }

    fun replaceElement(element: M) {
        if (this.data!!.contains(element)) {
            val position: Int = data!!.indexOf(element)
            this.data!![position] = element
            this.notifyItemChanged(position)
        }
    }

    interface BaseAdapterListener<M> {

        fun onClickItem(item: M, position: Int)

        fun onLoadingShow()
    }
}
