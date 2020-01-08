package ru.gorshkov.revoluttask.db.converter

import androidx.room.TypeConverter
import ru.gorshkov.revoluttask.pojo.RevolutCurrency

class RevolutCurrencyConverter {
    @TypeConverter
    fun fromCurrency(value: RevolutCurrency): String = value.name

    @TypeConverter
    fun toCurrency(value: String): RevolutCurrency = RevolutCurrency.valueOf(value)
}