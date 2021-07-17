package com.karim.currencyconverter.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class LatestRatesResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "rates") val rates: Map<String, String>
) : Parcelable

