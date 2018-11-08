package br.com.douglas.tvshow.network.vo

import android.arch.persistence.room.*

@Entity
class Externals constructor(tvShowId: Long, imdb: String?) {

    @PrimaryKey(autoGenerate = false)
    var tvShowId: Long? = tvShowId

    @ColumnInfo(name = "imdb")
    var imdb: String? = imdb
}