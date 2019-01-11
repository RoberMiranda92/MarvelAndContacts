package com.verse.app.verseapp.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity : AppCompatActivity(), BasePresenter.BaseView {


  private val TAG_ERROR_DIALOG = "dialogError"
  private var isErrorShowing: Boolean = false
  private var errorDialogFragment: ErrorDialogFragment? = null

  private val TAG_SPINNER_DIALOG = "dialogSpinner"
  private var isLoadingShowing: Boolean = false
  private var spinnerDialogFragment: SpinnerDialogFragment? = null


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(getLayout())
    initPresenter()
    intViews()
    decorateViews()
    getData()

  }

  abstract fun intViews()

  abstract fun decorateViews()

  override fun showLoading() {
    showProgressSpinner()
  }

  override fun hideLoading() {
    hideProgressSpinner()
  }


  override fun showGenericError() {
    showErrorDialog()
  }

  protected fun showMessage(message: String) {
    toast(message)
  }


  private fun showErrorDialog() {
    errorDialogFragment = supportFragmentManager.findFragmentByTag(TAG_ERROR_DIALOG) as ErrorDialogFragment?

    if (errorDialogFragment == null) {
      errorDialogFragment = ErrorDialogFragment.newInstance()
    }
    isErrorShowing = true
    if (!errorDialogFragment?.isAdded()!!) {
      supportFragmentManager.beginTransaction()
          .add(errorDialogFragment, TAG_ERROR_DIALOG)
          .commitAllowingStateLoss()

    }
  }

  private fun showProgressSpinner() {

    spinnerDialogFragment = supportFragmentManager.findFragmentByTag(TAG_SPINNER_DIALOG) as SpinnerDialogFragment?

    if (spinnerDialogFragment == null) {
      spinnerDialogFragment = SpinnerDialogFragment.newInstance()
    }
    isLoadingShowing = true
    if (!spinnerDialogFragment?.isAdded()!!) {
      supportFragmentManager.beginTransaction()
          .add(spinnerDialogFragment, TAG_SPINNER_DIALOG)
          .commitAllowingStateLoss()
    }
  }

  private fun hideProgressSpinner() {
    if (spinnerDialogFragment != null) {
      isLoadingShowing = false
      spinnerDialogFragment?.dismiss()
      spinnerDialogFragment = null
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    deletePresenter()
  }

  override fun getViewContext(): Context {
    return this
  }

  override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
  }

  protected abstract fun getLayout(): Int

  protected abstract fun initPresenter()

  protected abstract fun deletePresenter()

  protected abstract fun getData()


}

fun BaseActivity.toast(message: String) {
  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}