package com.verse.app.verseapp.features.sendmoney.view.fragments

import android.support.v7.widget.LinearLayoutManager
import com.verse.app.verseapp.R
import com.verse.app.verseapp.base.BaseFragment
import com.verse.app.verseapp.features.sendmoney.view.SplitProcessBus
import com.verse.app.verseapp.features.sendmoney.view.SplitProcessPersister
import com.verse.app.verseapp.features.sendmoney.view.adapters.ContactResumeListAdapter
import kotlinx.android.synthetic.main.split_resume_fragment.*

class SplitResumeFragment : BaseFragment() {

    private lateinit var mContactAdapter: ContactResumeListAdapter

    companion object {
        fun newInstance(): SplitResumeFragment {
            return SplitResumeFragment()
        }
    }


    override fun deletePresenter() {
    }

    override fun setPresenter() {

    }

    override fun initViews() {

        mContactAdapter = ContactResumeListAdapter()
        rv_contact_list.layoutManager = LinearLayoutManager(context)
        rv_contact_list.adapter = mContactAdapter
    }

    override fun decorateViews() {
    }

    override fun getLayout(): Int = R.layout.split_resume_fragment


    override fun getData() {
    }

    override fun onFragmentShowed() {
        SplitProcessBus.instance.updateTitle(
            String.format(
                getString(R.string.verse_splitprocess_resume_title),
                SplitProcessPersister.instance.ammout
            )
        )
        SplitProcessBus.instance.showButton(true)

        mContactAdapter.data = SplitProcessPersister.instance.contactList

    }
}