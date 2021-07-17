package com.karim.currencyconverter.hiltobjects

import com.karim.currencyconverter.network.RatesClient
import com.karim.currencyconverter.persistence.RatesDao
import com.karim.currencyconverter.repository.RatesRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideRatesRepository(
        ratesClient: RatesClient,
        ratesDao: RatesDao,
        moshi : Moshi
    ): RatesRepository {
        return RatesRepository(ratesClient, ratesDao, moshi)
    }
}