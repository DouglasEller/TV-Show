package br.com.douglas.tvshow.ui.home

import android.app.AlertDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.douglas.tvshow.R
import br.com.douglas.tvshow.base.BaseFragment
import br.com.douglas.tvshow.database.AppDataBase
import br.com.douglas.tvshow.network.vo.TVShowsResponse
import br.com.douglas.tvshow.ui.favorite.FavoriteAdapter
import br.com.douglas.tvshow.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : BaseFragment(), HomeCallback, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var db: AppDataBase
    private lateinit var viewModel: HomeViewModel
    private var tvShowsList: MutableList<TVShowsResponse> = arrayListOf()

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        view.sw_home.setOnRefreshListener(this)
        callShowsList()
        return view
    }

    override fun onLoadTVShows(tvShowsResponse: List<TVShowsResponse>) {
        dismissLoad()
        activity!!.runOnUiThread {
            tvShowsList.clear()
        }
        tvShowsList = tvShowsResponse.toMutableList()
        updateList()
    }

    internal fun updateList() {
        tvShowsList.let {
            it.forEach { tvShow ->
                tvShow.isFavorite = db.tvShowDao().findById(tvShow.id!!) != null
            }
        }
        rv_shows_home.layoutManager = LinearLayoutManager(context)
        rv_shows_home.adapter = TVShowsAdapter(tvShowsList, context!!, db)
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
