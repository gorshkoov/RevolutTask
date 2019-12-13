package ru.gorshkov.revoluttask.features.currencies.repositories

import ru.gorshkov.revoluttask.pojo.RevolutRate

interface CurrencyRepository {
    suspend fun getCurrencyRate(base : String) : RevolutRate
}