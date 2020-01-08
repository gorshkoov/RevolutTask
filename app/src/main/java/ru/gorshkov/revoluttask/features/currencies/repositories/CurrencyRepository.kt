package ru.gorshkov.revoluttask.features.currencies.repositories

import ru.gorshkov.revoluttask.db.entities.CurrencyEntity
import ru.gorshkov.revoluttask.pojo.RevolutCurrency

interface CurrencyRepository {
    suspend fun loadCurrencies(): MutableList<CurrencyEntity>

    fun updateAmount(strValue: String?)

    fun updateCurrency(currency: RevolutCurrency)
}