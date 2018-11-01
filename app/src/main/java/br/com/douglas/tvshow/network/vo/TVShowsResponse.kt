package br.com.douglas.tvshow.network.vo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class TVShowsResponse (
        @PrimaryKey(autoGenerate = false)
        val id: Long? = null,

        @ColumnInfo(name = "validated")
        val name: String? = "",

        @ColumnInfo(name = "validated")
        val summary: String? = "",

        @ColumnInfo(name = "image")
        val image: Image? = null
) : Parcelable

