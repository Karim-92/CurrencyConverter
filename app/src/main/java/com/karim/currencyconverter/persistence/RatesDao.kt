package com.karim.currencyconverter.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karim.currencyconverter.model.Rate

@Dao
interface RatesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rates: List<Rate>)

    @Query("SELECT * FROM rates")
    suspend fun getAllRates() : List<Rate>

    @Query("DELETE FROM rates")
    suspend fun clearRates()
}