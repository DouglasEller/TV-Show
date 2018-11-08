package br.com.douglas.tvshow.network.vo

import android.arch.persistence.room.*

@Entity
class Image constructor(tvShowId: Long, medium: String, original: String) {

    @PrimaryKey(autoGenerate = false)
    var tvShowId: Long? = tvShowId

    @ColumnInfo(name = "medium")
    var medium: String? = medium

    @ColumnInfo(name = "original")
    var original: String? = original
}