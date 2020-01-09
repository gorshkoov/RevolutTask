package ru.gorshkov.revoluttask.db.converter

import androidx.room.TypeConverter
import ru.gorshkov.revoluttask.pojo.RevolutCurrency
import java.math.BigDecimal

class BigDecimalConverter {
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal): String = value.toString()

    @TypeConverter
    fun toBigDecimal(value: String): BigDecimal = BigDecimal(value)
}