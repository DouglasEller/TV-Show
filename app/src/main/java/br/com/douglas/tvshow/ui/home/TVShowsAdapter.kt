package br.com.douglas.tvshow.ui.home

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.douglas.tvshow.R
import br.com.douglas.tvshow.database.AppDataBase
import br.com.douglas.tvshow.network.vo.TVShowsResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_tv_show.view.*

class TVShowsAdapter(var tvShowsList: MutableList<TVShowsResponse>,
                     private val context: Context,
                     private val db: AppDataBase)
    : Adapter<TVShowsAdapter.TVShowsViewHolder>() {

    override fun onBindViewHolder(holder: TVShowsViewHolder, position: Int) {
        val tvShow = tvShowsList[position]
        holder.bindView(tvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TVShowsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_tv_show, parent, false)
        return TVShowsViewHolder(view, context, db, this)
    }

    override fun getItemCount(): Int {
        return tvShowsList.size
    }

    class TVShowsViewHolder(itemView: View,
                            private val context: Context,
                            private val db: AppDataBase,
                            private val tvShowsAdapter: TVShowsAdapter) : RecyclerView.ViewHolder(itemView) {

        private var tvShow: TVShowsResponse? = null

        fun bindView(tvShowsResponse: TVShowsResponse) {
            tvShow = tvShowsResponse
            if (tvShowsResponse.isFavorite)
                itemView.iv_action_icon_item.setImageDrawable(context.getDrawable(R.drawable.ic_remove))
            else
                itemView.iv_action_icon_item.setImageDrawable(context.getDrawable(R.drawable.ic_save))

            itemView.tv_subtitle_item.text = tvShowsResponse.name

            Glide.with(itemView.iv_tv_show_item.context)
                    .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_placeholder))
                    .load(tvShowsResponse.image?.medium)
                    .into(itemView.iv_tv_show_item)

            itemView.iv_action_icon_item.setOnClickListener {
                showAlertSaveRemove()
            }
        }

        private fun showAlertSaveRemove() {
            val alertDialog = AlertDialog.Builder(context).create()

            if (tvShow!!.isFavorite) {
                alertDialog.setMessage(Html.fromHtml(context.getString(R.string.msg_removing_favorite)))
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getString(R.string.alert_remove_btn), clickButtonRemove)
            } else {
                alertDialog.setMessage(Html.fromHtml(context.getString(R.string.msg_saving_favorite)))
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getString(R.string.alert_save_btn), clickButtonSave)
            }
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, context.getString(R.string.alert_negative_btn)) { dialog, _ -> dialog.dismiss() }
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

        private val clickButtonSave = DialogInterface.OnClickListener { _, _ ->
            itemView.iv_action_icon_item.setImageDrawable(context.getDrawable(R.drawable.ic_save))
            tvShow?.image?.tvShowId = tvShow?.id
            tvShow?.isFavorite = true
            db.tvShowDao().insert(tvShow!!)
            db.imageDao().insert(tvShow?.image!!)
            tvShowsAdapter.notifyDataSetChanged()
        }

        private val clickButtonRemove = DialogInterface.OnClickListener { _, _ ->
            itemView.iv_action_icon_item.setImageDrawable(context.getDrawable(R.drawable.ic_remove))
            db.tvShowDao().deleteById(tvShow?.id!!)
            tvShow?.isFavorite = false
            tvShowsAdapter.notifyDataSetChanged()
        }
    }
}