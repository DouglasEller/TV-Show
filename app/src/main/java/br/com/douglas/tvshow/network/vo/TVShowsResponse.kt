package br.com.douglas.tvshow.network.vo

import com.google.gson.annotations.SerializedName

class TVShowsResponse (
        val id: Long? = null,
        val name: String? = "",
        val summary: String? = "",

        @SerializedName("image")
        val image: Image? = null
)

