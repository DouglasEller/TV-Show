package br.com.douglas.tvshow.ui.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.douglas.tvshow.R
import br.com.douglas.tvshow.database.AppDataBase
import br.com.douglas.tvshow.network.vo.TVShowsResponse
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment() {

    private lateinit var db: AppDataBase

    private var tvShowsList: MutableList<TVShowsResponse> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = AppDataBase.getInstance(activity!!)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    internal fun updateList() {
        activity!!.runOnUiThread {
            tvShowsList.clear()
        }
        tvShowsList = db.tvShowDao().getAll().toMutableList()
        tvShowsList.let {
            it.forEach { tvShow ->
                tvShow.image = db.imageDao().findById(tvShow.id!!)
                tvShow.externals = db.externalsDao().findById(tvShow.id!!)
            }
        }

        rv_shows_favorite.layoutManager = LinearLayoutManager(context)
        rv_shows_favorite.adapter = FavoriteAdapter(tvShowsList, context!!, db)
    }
}
