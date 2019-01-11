package com.verse.app.verseapp.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment(), BasePresenter.BaseView {

    protected val baseActivity: BaseActivity?
        get() {
            return if (activity != null) (activity as BaseActivity) else null
        }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val mainView = inflater.inflate(getLayout(), container, false)

        setPresenter()

        return mainView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        decorateViews()
        getData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (context != null)
            if (isVisibleToUser) {
                onFragmentShowed()
            }
    }


    override fun showLoading() {
        if (isAdded)
            (activity as BaseActivity).showLoading()
    }

    override fun hideLoading() {
        if (isAdded)
            (activity as BaseActivity).hideLoading()
    }


    override fun showGenericError() {
        if (isAdded)
            (activity as BaseActivity).showGenericError()
    }


    override fun getViewContext(): Context {
        return context!!
    }

    override fun onDestroy() {
        super.onDestroy()
        deletePresenter()
    }

    abstract fun setPresenter()

    abstract fun initViews()

    abstract fun decorateViews()

    abstract fun getLayout(): Int

    abstract fun getData()

    abstract fun onFragmentShowed()

    protected abstract fun deletePresenter()

}

