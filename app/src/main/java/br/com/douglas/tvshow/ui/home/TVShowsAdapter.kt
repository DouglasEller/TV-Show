package br.com.douglas.tvshow.ui.home

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.douglas.tvshow.R
import br.com.douglas.tvshow.network.vo.TVShowsResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_tv_show.view.*

class TVShowsAdapter(private val tvShowsList: List<TVShowsResponse>) : Adapter<TVShowsAdapter.TVShowsViewHolder>() {

    override fun onBindViewHolder(holder: TVShowsViewHolder, position: Int) {
        val tvShow = tvShowsList[position]
        holder.bindView(tvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TVShowsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_tv_show, parent, false)
        return TVShowsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tvShowsList.size
    }

    class TVShowsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindView(tvShow: TVShowsResponse) {
            itemView.tv_subtitle_item.text = tvShow.name

            Glide.with(itemView.iv_tv_show_item.context)
                    .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_placeholder))
                    .load(tvShow.image?.medium)
                    .into(itemView.iv_tv_show_item)
        }
    }
}