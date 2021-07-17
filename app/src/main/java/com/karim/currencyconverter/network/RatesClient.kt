package com.karim.currencyconverter.network

import com.karim.currencyconverter.BuildConfig
import com.karim.currencyconverter.model.LatestRatesResponse
import javax.inject.Inject

class RatesClient @Inject constructor(private val ratesService: RatesService) {

    suspend fun getRatesRemote(): LatestRatesResponse =
        ratesService.getRemoteRates(accessKey = BuildConfig.API_KEY, symbols = getSymbolsString())

    companion object {
        fun getSymbolsString(): String {
            return "USD,AUD,CAD,PLN,MXN,EGP,SAR,GBP,JPY,AED"
        }
    }
}