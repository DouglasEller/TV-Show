package br.com.douglas.tvshow.ui.home

import br.com.douglas.tvshow.base.BaseViewModel
import br.com.douglas.tvshow.network.repository.TVShowsRepository
import javax.inject.Inject

class HomeViewModel @Inject
internal constructor(private val userDataSource: TVShowsRepository) : BaseViewModel<>() {
}