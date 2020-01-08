package ru.gorshkov.revoluttask.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.gorshkov.revoluttask.db.AppDatabase
import ru.gorshkov.revoluttask.pojo.RevolutCurrency
import java.math.BigDecimal

@Entity(tableName = AppDatabase.CURRENCY_TABLE)
data class CurrencyEntity(
    @PrimaryKey
    val currency: RevolutCurrency,
    var value: String = BigDecimal.ONE.toString()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CurrencyEntity

        if (currency != other.currency) return false

        return true
    }

    override fun hashCode(): Int {
        return currency.hashCode()
    }
}