package com.karim.currencyconverter.network

import com.karim.currencyconverter.model.LatestRatesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesService {
    @GET("latest")
    suspend fun getRemoteRates(
        @Query("access_key") accessKey: String,
        @Query("symbols") symbols: String
    ) : LatestRatesResponse
}