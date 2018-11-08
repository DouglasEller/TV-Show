package br.com.douglas.tvshow.ui.favorite

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.douglas.tvshow.R
import br.com.douglas.tvshow.database.AppDataBase
import br.com.douglas.tvshow.network.vo.TVShowsResponse
import br.com.douglas.tvshow.ui.details.DetailsActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_tv_show.view.*

class FavoriteAdapter(var tvShowsList: MutableList<TVShowsResponse>,
                      private val context: Context,
                      private val db: AppDataBase)
    : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val tvShow = tvShowsList[position]
        holder.bindView(tvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_tv_show, parent, false)
        return FavoriteViewHolder(view, context, db, this)
    }

    override fun getItemCount(): Int {
        return tvShowsList.size
    }

    class FavoriteViewHolder(itemView: View,
                             private val context: Context,
                             private val db: AppDataBase,
                             private val favoriteAdapter: FavoriteAdapter) : RecyclerView.ViewHolder(itemView) {

        private var tvShow: TVShowsResponse? = null

        fun bindView(tvShowsResponse: TVShowsResponse) {
            tvShow = tvShowsResponse

            itemView.iv_action_icon_item.setImageDrawable(context.getDrawable(R.drawable.ic_remove))


            itemView.tv_subtitle_item.text = tvShowsResponse.name

            Glide.with(itemView.iv_tv_show_item.context)
                    .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_placeholder))
                    .load(tvShowsResponse.image?.medium)
                    .into(itemView.iv_tv_show_item)

            itemView.iv_action_icon_item.setOnClickListener {
                showAlertSaveRemove()
            }

            itemView.setOnClickListener {
                startDetails()
            }
        }

        private fun startDetails() {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("TV_SHOW_BUNDLE", getTVShowBundle())
            context.startActivity(intent)
        }

        private fun showAlertSaveRemove() {
            val alertDialog = AlertDialog.Builder(context).create()

            alertDialog.setMessage(Html.fromHtml(context.getString(R.string.msg_removing_favorite)))
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getString(R.string.alert_remove_btn), clickButtonRemove)
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, context.getString(R.string.alert_negative_btn)) { dialog, _ -> dialog.dismiss() }
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

        private val clickButtonRemove = DialogInterface.OnClickListener { _, _ ->
            itemView.iv_action_icon_item.setImageDrawable(context.getDrawable(R.drawable.ic_remove))
            db.tvShowDao().deleteById(tvShow?.id!!)
            favoriteAdapter.tvShowsList.remove(tvShow!!)
            favoriteAdapter.notifyDataSetChanged()
        }

        private fun getTVShowBundle(): Bundle {
            val bundle = Bundle()
            bundle.putString("NAME", tvShow?.name)
            bundle.putString("SUMMARY", tvShow?.summary)
            bundle.putString("IMDB", tvShow?.externals?.imdb)
            bundle.putString("IMAGE_URL", tvShow?.image?.original)
            return bundle
        }
    }
}