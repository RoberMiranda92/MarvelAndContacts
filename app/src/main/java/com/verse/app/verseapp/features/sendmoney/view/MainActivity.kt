package com.verse.app.verseapp.features.sendmoney.view

import com.verse.app.verseapp.R
import com.verse.app.verseapp.base.BaseActivity
import com.verse.app.verseapp.features.sendmoney.view.adapters.NewSplitProcessAdapter
import com.verse.app.verseapp.hide
import com.verse.app.verseapp.show
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override fun intViews() {
        setSupportActionBar(toolbar)
        nsvp_pager.adapter = NewSplitProcessAdapter(supportFragmentManager)
        nsvp_pager.currentItem = 0
        nsvp_pager.offscreenPageLimit = 3

        SplitProcessBus.instance.fragmentEventSubject
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                updateButtonText(
                    String.format(
                        getString(R.string.verse_splitprocess_button_split_between),
                        SplitProcessPersister.instance.contactList.size
                    )
                )
            }

        bt_split.setOnClickListener {
            nsvp_pager.next()

        }

        SplitProcessBus.instance.activityEventSubject
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                if (result)
                    bt_split.show()
                else
                    bt_split.hide()
            }


        SplitProcessBus.instance.changeTitleEventSubject
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                title = result
            }

        SplitProcessBus.instance.buttonEventSubject
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                nsvp_pager.next()
            }

    }

    private fun updateButtonText(text: String) {
        bt_split.text = text
    }

    override fun decorateViews() {
    }

    override fun getLayout(): Int = R.layout.activity_main


    override fun initPresenter() {
    }

    override fun onDestroy() {
        super.onDestroy()
        SplitProcessPersister.instance.clear()
    }

    override fun deletePresenter() {

    }

    override fun getData() {
    }

    override fun onBackPressed() {
        if (nsvp_pager.isfirsElement) {
            super.onBackPressed()
        } else {
            nsvp_pager.prev()
        }
    }
}