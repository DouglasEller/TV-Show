package br.com.douglas.tvshow.network.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations.map
import br.com.douglas.tvshow.network.api.API
import br.com.douglas.tvshow.network.api.ApiResponse
import br.com.douglas.tvshow.network.vo.TVShowsResponse

class TVShowsRepository(private val apiService: API) : TVShowsDataSource {

    override fun getTVShows(page: Int): LiveData<ApiResponse<List<TVShowsResponse>>> {
    return map(apiService.requestTVShows(page)) { response -> response }
    }
}
