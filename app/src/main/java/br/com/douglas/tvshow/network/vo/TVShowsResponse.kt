package br.com.douglas.tvshow.network.vo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
class TVShowsResponse constructor(id: Long, name: String, summary: String, url: String, isFavorite: Boolean) {
    @PrimaryKey(autoGenerate = false)
    var id: Long? = id

    @ColumnInfo(name = "name")
    var name: String? = name

    @ColumnInfo(name = "summary")
    var summary: String? = summary

    @ColumnInfo(name = "url")
    var url: String? = url

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = isFavorite

    @Ignore
    var image: Image? = null
}

