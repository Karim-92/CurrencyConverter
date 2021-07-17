package com.karim.currencyconverter.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karim.currencyconverter.model.Rate

@Database(entities = [Rate::class], version = 1, exportSchema = false)
abstract class RatesDatabase : RoomDatabase() {
    abstract fun ratesDao() : RatesDao
}