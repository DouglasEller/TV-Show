package br.com.douglas.tvshow.network.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Image(
        val medium: String? = "",
        val original: String? = ""
): Parcelable