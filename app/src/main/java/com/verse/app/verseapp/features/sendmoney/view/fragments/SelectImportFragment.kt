package com.verse.app.verseapp.features.sendmoney.view.fragments

import com.verse.app.verseapp.R
import com.verse.app.verseapp.base.BaseFragment
import com.verse.app.verseapp.commons.DecimalFilter
import com.verse.app.verseapp.commons.InputMinMaxFilter
import com.verse.app.verseapp.features.sendmoney.model.entity.Contact
import com.verse.app.verseapp.features.sendmoney.view.SplitProcessBus
import com.verse.app.verseapp.features.sendmoney.view.SplitProcessPersister
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.select_import_fragment.*

class SelectImportFragment : BaseFragment() {

    companion object {
        const val MIN_VALUE: Double = 0.01
        const val MAX_VALUE: Double = 1000.00

        fun newInstance(): SelectImportFragment {
            return SelectImportFragment()
        }
    }


    override fun deletePresenter() {
    }

    override fun setPresenter() {

    }

    override fun initViews() {
        et_amount.filters = arrayOf(InputMinMaxFilter(MIN_VALUE, MAX_VALUE), DecimalFilter(2))
        bt_continue.setOnClickListener {
            if (!et_amount?.text.isNullOrEmpty()) {
                SplitProcessPersister.instance.ammout = et_amount?.text.toString().toDouble()
                var ammountPerContact =
                    SplitProcessPersister.instance.ammout / SplitProcessPersister.instance.contactList.size
                for (contact: Contact in SplitProcessPersister.instance.contactList) {
                    contact.ammount = ammountPerContact
                }

                SplitProcessBus.instance.onButtonClicked()
            }
        }
    }

    override fun decorateViews() {
    }

    override fun getLayout(): Int = R.layout.select_import_fragment

    override fun getData() {
    }

    private var disponsable: Disposable? = null

    override fun onFragmentShowed() {
        et_amount.setText(SplitProcessPersister.instance.ammout.toString())
        SplitProcessBus.instance.updateTitle(getString(R.string.verse_splitprocess_import_title))
        SplitProcessBus.instance.showButton(false)

    }

    override fun onPause() {
        super.onPause()
        disponsable?.dispose()
    }
}