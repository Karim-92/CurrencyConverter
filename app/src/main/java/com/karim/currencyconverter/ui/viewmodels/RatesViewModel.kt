package com.karim.currencyconverter.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karim.currencyconverter.model.Rate
import com.karim.currencyconverter.repository.RatesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RatesViewModel @Inject constructor(
    private val ratesRepository: RatesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _navigateToConversion= MutableLiveData<Rate?>()
    val navigateToConverstion
        get() = _navigateToConversion
    var job: Job? = null
    var itemList = ratesRepository.ratesList
    val selectedRate = MutableLiveData<Rate>()
    var calculatedValue: Double = 0.0
    var online: Boolean = false

    init {
        Timber.d("Loading JSON Assets into ViewModel.")
        loadJsonFromAssets()
    }

    fun loadJsonFromAssets() {
        job = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                ratesRepository.getRates(online)
            }
        }
    }

    fun convertValue(unconvertedValue: Double) {
        calculatedValue = (selectedRate.value?.rate ?: 0.0) * unconvertedValue
    }

    fun onItemClick(item: Rate) {
        _navigateToConversion.value = item
    }
    fun navigationComplete(){
        _navigateToConversion.value = null
    }
}