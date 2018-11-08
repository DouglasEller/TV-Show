package br.com.douglas.tvshow.ui.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import br.com.douglas.tvshow.R
import br.com.douglas.tvshow.database.AppDataBase
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : AppCompatActivity() {

    private lateinit var db: AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        db = AppDataBase.getInstance(this)!!
        val bundle = intent.getBundleExtra("TV_SHOW_BUNDLE")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = bundle.getString("NAME")
        tv_description.text = Html.fromHtml(bundle.getString("SUMMARY"))
        tv_link.text = getString(R.string.url_imdb) + bundle.getString("IMDB")

        Glide.with(iv_poster.context)
                .load(bundle.getString("IMAGE_URL"))
                .apply(RequestOptions().placeholder(R.drawable.ic_placeholder))
                .into(iv_poster)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
