package br.com.douglas.tvshow.ui.home

import android.app.AlertDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.douglas.tvshow.R
import br.com.douglas.tvshow.base.BaseFragment
import br.com.douglas.tvshow.database.AppDataBase
import br.com.douglas.tvshow.network.vo.TVShowsResponse
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), HomeCallback, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var db: AppDataBase
    private lateinit var viewModel: HomeViewModel

    private val clickButtonPositive = DialogInterface.OnClickListener { _, _ ->
        callShowsList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = AppDataBase.getInstance(activity!!)!!

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
        tvShowsResponse.let {
            it.forEach { tvShow ->
                if (db.tvShowDao().findById(tvShow.id!!) != null) {
                    tvShow.isFavorite = true
                }
            }
        }

        rv_shows_home.layoutManager = LinearLayoutManager(context)
        rv_shows_home.adapter = TVShowsAdapter(tvShowsResponse, context!!, db)
    }

    override fun onError() {
        dismissLoad()

        val alertDialog = AlertDialog.Builder(activity).create()
        alertDialog.setTitle(getString(R.string.title_error))
        alertDialog.setMessage(Html.fromHtml(getString(R.string.msg_error)))
        alertDialog.setCancelable(false)
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.alert_positive_btn), clickButtonPositive)
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.alert_negative_btn)) { dialog, _ -> dialog.dismiss() }
        alertDialog.show()
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
