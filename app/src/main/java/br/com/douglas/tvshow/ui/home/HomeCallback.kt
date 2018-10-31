package br.com.douglas.tvshow.ui.home

import br.com.douglas.tvshow.network.vo.TVShowsResponse

interface HomeCallback {

    fun onLoadTVShows(tvShowsResponse: List<TVShowsResponse>)
    fun onError()
}