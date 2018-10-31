package br.com.douglas.tvshow.network.api

import android.arch.lifecycle.LiveData
import br.com.douglas.tvshow.network.vo.TVShowsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("/shows")
    fun requestTVShows(@Query("page") page: Int ): LiveData<ApiResponse<List<TVShowsResponse>>>
}