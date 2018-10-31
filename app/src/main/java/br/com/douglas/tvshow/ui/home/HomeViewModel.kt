package br.com.douglas.tvshow.ui.home

import android.arch.lifecycle.Observer
import br.com.douglas.tvshow.base.BaseViewModel
import br.com.douglas.tvshow.network.api.API
import br.com.douglas.tvshow.network.api.ApiResponse
import br.com.douglas.tvshow.network.repository.TVShowsDataSource
import br.com.douglas.tvshow.network.repository.TVShowsRepository
import br.com.douglas.tvshow.network.vo.TVShowsResponse
import br.com.douglas.tvshow.ui.main.MainPresenter
import javax.inject.Inject

class HomeViewModel @Inject
internal constructor(private val tvShowsDataSource: TVShowsDataSource): BaseViewModel<HomeCallback>(){

    lateinit var callBack: HomeCallback
    private var tvShowsList: List<TVShowsResponse> = ArrayList()

    fun loadTVShows() {
        tvShowsDataSource.getTVShows(100).observe(getLifecycleOwner(),
                Observer<ApiResponse<List<TVShowsResponse>>> { response ->
                    run {
                        response?.let {
                            when (response.isSuccessful) {
                                true -> {
                                    tvShowsList = response.body!!
                                    callBack.onLoadTVShows(tvShowsList)
                                }
                                false -> {
                                    callBack.onError()
                                }
                            }
                        }
                    }
                })
    }
}