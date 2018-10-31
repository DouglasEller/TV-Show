package br.com.douglas.tvshow.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.douglas.tvshow.R
import br.com.douglas.tvshow.base.BaseFragment
import br.com.douglas.tvshow.network.vo.TVShowsResponse
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), HomeCallback, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        viewModel.setLifecycleOwner(this)
        viewModel.callBack = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        callShowsList()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sw_home.setOnRefreshListener(this)

    }

    override fun onLoadTVShows(tvShowsResponse: List<TVShowsResponse>) {
        dismissLoad()
        rv_shows_home.layoutManager = LinearLayoutManager(context)
        rv_shows_home.adapter = TVShowsAdapter(tvShowsResponse)
    }

    override fun onError() {
        dismissLoad()
    }

    override fun onRefresh() {
        callShowsList()
        sw_home.isRefreshing = false
    }

    private fun callShowsList() {
        showLoad()
        viewModel.loadTVShows()
    }
}
