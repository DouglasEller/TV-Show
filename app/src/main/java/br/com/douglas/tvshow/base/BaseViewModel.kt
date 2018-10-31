package br.com.douglas.tvshow.base

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModel
import java.lang.ref.WeakReference

open class BaseViewModel<N> : ViewModel() {

    private lateinit var mOwner: LifecycleOwner

    private var mIsLoading: Boolean = false

    private var mNavigator: WeakReference<N>? = null

    fun getNavigator(): N? {
        return mNavigator?.get()
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }

    fun getIsLoading(): Boolean {
        return mIsLoading
    }

    fun setIsLoading(isLoading: Boolean) {
        mIsLoading = isLoading
    }

    fun setLifecycleOwner(owner: LifecycleOwner){
        mOwner = owner
    }

    fun getLifecycleOwner(): LifecycleOwner {
        return mOwner
    }
}