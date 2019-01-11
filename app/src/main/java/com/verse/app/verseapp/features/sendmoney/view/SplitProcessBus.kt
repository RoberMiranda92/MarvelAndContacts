package com.verse.app.verseapp.features.sendmoney.view

import io.reactivex.subjects.PublishSubject


class SplitProcessBus {

    private object Holder {
        val INSTANCE = SplitProcessBus()
    }

    var fragmentEventSubject: PublishSubject<SplitProcessVoid> = PublishSubject.create<SplitProcessVoid>()
    var changeTitleEventSubject: PublishSubject<String> = PublishSubject.create<String>()
    var activityEventSubject: PublishSubject<Boolean> = PublishSubject.create<Boolean>()
    var buttonEventSubject: PublishSubject<SplitProcessVoid> = PublishSubject.create<SplitProcessVoid>()


    companion object {
        val instance: SplitProcessBus by lazy { Holder.INSTANCE }
    }


    fun updateFragmentText() {
        fragmentEventSubject.onNext(SplitProcessVoid())
    }

    fun updateTitle(title: String) {
        changeTitleEventSubject.onNext(title)
    }

    fun showButton(show:Boolean) {
        activityEventSubject.onNext(show)
    }

    fun onButtonClicked() {
        buttonEventSubject.onNext(SplitProcessVoid())
    }

    class SplitProcessVoid {

    }

}


