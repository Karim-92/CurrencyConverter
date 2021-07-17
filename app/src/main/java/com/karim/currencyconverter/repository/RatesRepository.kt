package com.karim.currencyconverter.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.karim.currencyconverter.model.Rate
import com.karim.currencyconverter.model.RateTypeConverter
import com.karim.currencyconverter.network.RatesClient
import com.karim.currencyconverter.persistence.RatesDao
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


/**
 * Steps: Using database as single source of truth.
 * First flow: Check if database is empty, fill with JSON file in assets and continue.
 * Second flow: Database not empty, using offline mode. Continue flow.
 * Third flow: Online program. Load from remote service, fill into database and JSON file.
 */

class RatesRepository @Inject constructor(
    private val ratesClient: RatesClient,
    private val ratesDao: RatesDao,
    private val moshi: Moshi
) {
    val ratesList = MutableLiveData<List<Rate>>()

    suspend fun getRates(online: Boolean) {
        var rates: List<Rate> = emptyList()
        if (online) {
            val ratesResponse = ratesClient.getRatesRemote()
            rates = ratesResponse.rates.map {
                Rate(it.key, it.value.toDouble())
            }
            if (!rates.isNullOrEmpty()) {
                Timber.d("Rates from online: $rates")
                ratesDao.insertAll(rates)
                ratesList.postValue(rates)
            }
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                ratesDao.clearRates()
                rates = ratesDao.getAllRates()
                Timber.d("Rates list from DB")
            }
            if (rates.isNullOrEmpty()) {
                try {
                    val offlineJson =
                        "{\n" +
                                "    \"USD\":1.18054,\n" +
                                "    \"AUD\":1.594678,\n" +
                                "    \"CAD\":1.489122,\n" +
                                "    \"PLN\":4.584243,\n" +
                                "    \"MXN\":23.483897,\n" +
                                "    \"EGP\":18.541,\n" +
                                "    \"SAR\":4.4283,\n" +
                                "    \"GBP\":0.857047,\n" +
                                "    \"JPY\":129.971598,\n" +
                                "    \"AED\":4.33601\n" +
                                "}\n" +
                                "\n"
                    val rateTypeConverter = RateTypeConverter(moshi)
                    val ratesMap: Map<String, String>? = rateTypeConverter.rateFromJson(offlineJson)
                    // Turn map into a list of rates
                    if (!ratesMap.isNullOrEmpty()) {
                        rates = ratesMap.map {
                            Rate(it.key, it.value.toDouble())
                        }
                        Timber.d("Rates from JSON: $rates")
                        CoroutineScope(Dispatchers.IO).launch {
                            ratesDao.clearRates()
                            ratesDao.insertAll(rates = rates)
                        }
                    }
                } catch (e: IOException) {
                    Timber.e("Exception occurred while parsing JSON: $e")
                } finally {
                    ratesList.postValue(rates)
                }
            } else {
                ratesList.postValue(rates)
            }
        }
    }

}