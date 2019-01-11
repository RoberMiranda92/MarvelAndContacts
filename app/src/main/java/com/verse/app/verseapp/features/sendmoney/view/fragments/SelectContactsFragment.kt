package com.verse.app.verseapp.features.sendmoney.view.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import com.verse.app.verseapp.R
import com.verse.app.verseapp.base.BaseFragment
import com.verse.app.verseapp.base.list.BaseListAdapter
import com.verse.app.verseapp.features.sendmoney.model.entity.Contact
import com.verse.app.verseapp.features.sendmoney.presenter.MainPresenter
import com.verse.app.verseapp.features.sendmoney.view.SplitProcessBus
import com.verse.app.verseapp.features.sendmoney.view.SplitProcessPersister
import com.verse.app.verseapp.features.sendmoney.view.adapters.ContactListAdapter
import com.verse.app.verseapp.toast
import kotlinx.android.synthetic.main.split_resume_fragment.*
import org.koin.android.ext.android.inject
import java.util.*

class SelectContactsFragment : BaseFragment(), MainPresenter.MainView, BaseListAdapter.BaseAdapterListener<Contact>,
    ContactListAdapter.ContactListAdapterListener {


    private val mPresenter: MainPresenter by inject()
    private lateinit var mContactAdapter: ContactListAdapter

    companion object {
        const val PERMISSIONS_REQUEST_READ_CONTACTS = 100

        fun newInstance(): SelectContactsFragment {
            return SelectContactsFragment()
        }
    }

    override fun setPresenter() {
        mPresenter.mView = this
    }

    override fun initViews() {
        mContactAdapter = ContactListAdapter(this)
        rv_contact_list.layoutManager = LinearLayoutManager(context)
        rv_contact_list.adapter = mContactAdapter

    }

    override fun decorateViews() {
    }

    override fun getLayout(): Int = R.layout.select_contacts_fragment

    override fun getData() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && baseActivity?.checkSelfPermission(
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                PERMISSIONS_REQUEST_READ_CONTACTS
            )
        } else {
            mPresenter.getAllContacts()

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mPresenter.getAllContacts()
            } else {
                toast("Permission must be granted in order to display contacts information")
            }
        }
    }


    override fun onFragmentShowed() {
        SplitProcessBus.instance.updateTitle(getString(R.string.verse_splitprocess_select_title))
        SplitProcessBus.instance.showButton(true)

    }

    override fun deletePresenter() {
        mPresenter.onDestroy()
    }

    override fun onContactsLoaded(contactList: List<Contact>) {
        mContactAdapter.data = ArrayList(contactList)
    }

    override fun onElementSelected(contact: Contact) {
        SplitProcessPersister.instance.addContact(contact)
        SplitProcessBus.instance.updateFragmentText()
    }

    override fun onElementUnSelected(contact: Contact) {
        SplitProcessPersister.instance.removeContact(contact)
        SplitProcessBus.instance.updateFragmentText()
    }

    override fun onClickItem(item: Contact, position: Int) {
    }

    override fun onLoadingShow() {
    }

}
