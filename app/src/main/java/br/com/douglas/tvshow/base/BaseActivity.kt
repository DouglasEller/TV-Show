package br.com.douglas.tvshow.base

import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.douglas.tvshow.utils.WaitDialog
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private var waitDialog: WaitDialog? = null

    fun showWaitDialog() {
        if (waitDialog == null ) {
            waitDialog = WaitDialog(this)
            waitDialog?.show()
        }else if(!waitDialog?.isShowing!!){
            waitDialog?.show()
        }
    }

    fun dismissWaitDialog() {
        if (waitDialog != null && waitDialog?.isShowing!!) {
            waitDialog?.dismiss()
            waitDialog = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissWaitDialog()
    }
}