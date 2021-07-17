package com.karim.currencyconverter.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

class RateTypeConverter @Inject constructor(
    private val moshi: Moshi
){
    @FromJson fun rateFromJson(jsonString: String) : Map<String, String>? {
        val ratesType = Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
        val adapter: JsonAdapter<Map<String, String>> = moshi.adapter(ratesType)
        return adapter.fromJson(jsonString)
    }
}