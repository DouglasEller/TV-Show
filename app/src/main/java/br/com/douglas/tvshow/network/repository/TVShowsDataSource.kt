package br.com.douglas.tvshow.network.repository

import android.arch.lifecycle.LiveData
import br.com.douglas.tvshow.data.network.factory.api.ApiResponse
import br.com.douglas.tvshow.network.vo.TVShowsResponse

interface TVShowsDataSource {

    fun getTVShows(page: Int): LiveData<ApiResponse<TVShowsResponse>>

}
