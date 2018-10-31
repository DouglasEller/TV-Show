package br.com.douglas.tvshow.utils

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import br.com.douglas.tvshow.R

class WaitDialog(context: Context?) : ProgressDialog(context, R.style.DialogTheme) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_wait)
        setCancelable(false)
    }
}