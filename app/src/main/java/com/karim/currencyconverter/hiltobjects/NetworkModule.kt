package com.karim.currencyconverter.hiltobjects

import com.karim.currencyconverter.network.HttpInterceptor
import com.karim.currencyconverter.network.RatesClient
import com.karim.currencyconverter.network.RatesService
import com.karim.currencyconverter.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRatesService(retrofit: Retrofit): RatesService {
        return retrofit.create(RatesService::class.java)
    }

    @Provides
    @Singleton
    fun provideRatesClient(ratesService: RatesService): RatesClient {
        return RatesClient(ratesService)
    }


}