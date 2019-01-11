package com.verse.app.verseapp.features.sendmoney.presenter

import com.verse.app.verseapp.base.BasePresenter
import com.verse.app.verseapp.features.sendmoney.model.entity.Contact
import com.verse.app.verseapp.features.sendmoney.model.entity.ContactList
import com.verse.app.verseapp.features.sendmoney.model.interactors.GetAllContactsListOrdered
import com.verse.app.verseapp.koin.inject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter : BasePresenter<MainPresenter.MainView>() {
 
    private val getAllContactsListOrdered: GetAllContactsListOrdered by inject()

    fun getAllContacts() {
        mView?.showLoading()

        getAllContactsListOrdered.getAllContactsListOrdered()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe(
                { result ->
                    mView?.hideLoading()
                    mView?.onContactsLoaded(result)
                },
                {
                    onServiceError(it)
                })
    }

    interface MainView : BasePresenter.BaseView {

        fun onContactsLoaded(contactList: List<Contact>)
    }
}