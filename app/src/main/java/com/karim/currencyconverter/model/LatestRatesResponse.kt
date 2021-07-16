package com.karim.currencyconverter.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class LatestRatesResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "timestamp") val timestamp: Int,
    @Json(name = "base") val base: String,
    @Json(name = "date") val date: String,
    @Json(name = "rates") val rates: Rates
) : Parcelable

