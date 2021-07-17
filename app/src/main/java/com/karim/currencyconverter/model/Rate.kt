package com.karim.currencyconverter.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity(tableName = "rates")
@Parcelize
data class Rate(
    @PrimaryKey
    val name: String,
    val rate: Double
) : Parcelable {
    val flag: String
        get() = "file:///android_asset/${name.lowercase(Locale.getDefault())}.webp"
}