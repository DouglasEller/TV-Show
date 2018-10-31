package br.com.douglas.tvshow.network.api

import android.arch.lifecycle.LiveData
import br.com.douglas.tvshow.data.network.factory.api.ApiResponse
import br.com.douglas.tvshow.network.vo.TVShowsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("/shows")
    fun requestTVShows(@Path("page") page: Int ): LiveData<ApiResponse<TVShowsResponse>>
}