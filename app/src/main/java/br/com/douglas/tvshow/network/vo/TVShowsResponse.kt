package br.com.douglas.tvshow.network.vo

import android.arch.persistence.room.*

@Entity
class TVShowsResponse constructor(id: Long, name: String, summary: String, isFavorite: Boolean) {
    @PrimaryKey(autoGenerate = false)
    var id: Long? = id

    @ColumnInfo(name = "name")
    var name: String? = name

    @ColumnInfo(name = "summary")
    var summary: String? = summary

    @Ignore
    var externals: Externals? = null

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = isFavorite

    @Ignore
    var image: Image? = null
}

