package com.verse.app.verseapp.base

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.verse.app.verseapp.R

class SpinnerDialogFragment : DialogFragment() {

    companion object {
        fun newInstance(): SpinnerDialogFragment {
            val fragment = SpinnerDialogFragment()
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.spinner_dialog, container, false)
        setCancelable(false)
        return rootView
    }


    override fun getTheme(): Int  = R.style.TransparentDialog

    override fun dismiss() {
        this.dismissAllowingStateLoss()
    }

    override fun show(manager: FragmentManager, tag: String) {
        if (!this.isAdded) {
            try {
                manager.beginTransaction().add(this, tag).commitAllowingStateLoss()
            } catch (var4: Exception) {
                var4.printStackTrace()
            }

        }

    }
}
