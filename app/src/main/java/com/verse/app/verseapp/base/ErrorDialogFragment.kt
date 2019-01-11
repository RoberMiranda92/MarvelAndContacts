package com.verse.app.verseapp.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.AppCompatButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.verse.app.verseapp.R

class ErrorDialogFragment : DialogFragment() {

  companion object {
    fun newInstance(): ErrorDialogFragment {
      val fragment = ErrorDialogFragment()
      return fragment
    }
  }


  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


    val dialog = super.onCreateDialog(savedInstanceState)

    val flags = this.activity?.getWindow()?.attributes!!.flags
    if (flags and 1024 == 0 && dialog.window != null) {
      dialog.window!!.addFlags(2048)
    }
    dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



    return dialog
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(R.layout.error_dialog, container, false)
    setCancelable(false)
    initView(rootView)
    return rootView
  }

  private fun initView(rootView: View) {

    rootView.findViewById<AppCompatButton>(R.id.accept_button).setOnClickListener {
      this.dismiss()
    }

  }
}