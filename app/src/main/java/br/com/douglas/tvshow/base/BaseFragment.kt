package br.com.douglas.tvshow.base

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.support.v4.app.Fragment
import br.com.douglas.tvshow.utils.WaitDialog
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    private var waitDialog: WaitDialog? = null

    fun showLoad() {
        if (waitDialog == null) {
            waitDialog = WaitDialog(context)
            waitDialog?.show()
        } else if (!waitDialog?.isShowing!!) {
            waitDialog?.show()
        }
    }

    fun dismissLoad() {
        if (waitDialog != null && waitDialog?.isShowing!!) {
            waitDialog?.dismiss()
            waitDialog = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoad()
    }
}