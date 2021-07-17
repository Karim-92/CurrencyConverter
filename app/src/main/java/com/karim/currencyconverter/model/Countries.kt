package com.karim.currencyconverter.model
import android.os.Parcelable

import kotlinx.parcelize.Parcelize
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json


class Countries : ArrayList<CountriesItem>()

@JsonClass(generateAdapter = true)
@Parcelize
data class CountriesItem(
    @Json(name = "name") val name: String,
    @Json(name = "flag") val flag: String,
) : Parcelable
