package com.karim.currencyconverter.hiltobjects

import android.app.Application
import com.karim.currencyconverter.persistence.RatesDao
import com.karim.currencyconverter.persistence.RatesDatabase
import androidx.room.Room
import com.karim.currencyconverter.utils.RATES_DB
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): RatesDatabase {
        return Room
            .databaseBuilder(application, RatesDatabase::class.java, RATES_DB)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRatesDao(ratesDb: RatesDatabase): RatesDao {
        return ratesDb.ratesDao()
    }

}
