package com.verse.app.verseapp.base

import android.content.Context
import android.support.annotation.CallSuper

abstract class BasePresenter<V : BasePresenter.BaseView> {

    var mView: V? = null


    protected fun onServiceError(error: Throwable?) {
        mView?.hideLoading()
        mView?.showGenericError()

    }

    @CallSuper
    open fun onDestroy() {
        mView = null
    }

    interface BaseView {
        fun showLoading()
        fun hideLoading()
        fun showGenericError()
        fun getViewContext(): Context
    }
}