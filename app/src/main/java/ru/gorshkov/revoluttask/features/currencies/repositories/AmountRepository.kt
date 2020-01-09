package ru.gorshkov.revoluttask.features.currencies.repositories

import java.math.BigDecimal

interface AmountRepository {
    suspend fun getSavedAmount() : BigDecimal?

    suspend fun saveAmount(value : BigDecimal)
}