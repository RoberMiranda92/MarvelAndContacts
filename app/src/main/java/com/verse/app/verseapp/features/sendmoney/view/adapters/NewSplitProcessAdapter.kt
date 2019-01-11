package com.verse.app.verseapp.features.sendmoney.view.adapters

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.SparseArray
import com.verse.app.verseapp.base.BaseFragment
import com.verse.app.verseapp.features.sendmoney.view.fragments.SelectContactsFragment
import com.verse.app.verseapp.features.sendmoney.view.fragments.SelectImportFragment
import com.verse.app.verseapp.features.sendmoney.view.fragments.SplitResumeFragment

class NewSplitProcessAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    companion object {
        private const val NUM_ITEMS = 3
    }

    private var registeredFragments = SparseArray<BaseFragment>()


    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): BaseFragment? {
        val fragment: BaseFragment
        when (position) {
            0 -> fragment = SelectContactsFragment.newInstance()
            1 -> fragment = SelectImportFragment.newInstance()
            2 -> fragment = SplitResumeFragment.newInstance()

            else -> return null
        }

        registeredFragments.put(position, fragment)
        return fragment
    }

    fun getRegisteredFragment(position: Int): BaseFragment {
        return registeredFragments.get(position)
    }
}