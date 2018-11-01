package br.com.douglas.tvshow.network.vo

import android.arch.persistence.room.*

@Entity(foreignKeys = [
    ForeignKey(
            entity = TVShowsResponse::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("tvShowId"),
            onDelete = ForeignKey.CASCADE)
], indices = [Index(value = ["tvShowId"])])
class Image constructor(tvShowId: Long, medium: String, original: String) {

    @PrimaryKey(autoGenerate = false)
    var tvShowId: Long? = tvShowId

    @ColumnInfo(name = "medium")
    var medium: String? = medium

    @ColumnInfo(name = "original")
    var original: String? = original
}