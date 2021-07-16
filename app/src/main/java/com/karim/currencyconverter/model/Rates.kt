package com.karim.currencyconverter.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Rates(
    @Json(name = "AUD") val aUD: Double,
    @Json(name = "CAD") val cAD: Double,
    @Json(name = "CHF") val cHF: Double,
    @Json(name = "CNY") val cNY: Double,
    @Json(name = "EGP") val eGP: Double,
    @Json(name = "GBP") val gBP: Double,
    @Json(name = "JPY") val jPY: Double,
    @Json(name = "KWD") val kWD: Double,
    @Json(name = "PLN") val pLN: Double,
    @Json(name = "MXN") val mXN: Double,
    @Json(name = "SAR") val sAR: Double,
    @Json(name = "USD") val uSD: Double
) : Parcelable