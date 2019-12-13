package ru.gorshkov.revoluttask.features.currencies.repositories

import ru.gorshkov.revoluttask.network.CurrencyService
import ru.gorshkov.revoluttask.pojo.RevolutRate
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val service: CurrencyService
) : CurrencyRepository {

    override suspend fun getCurrencyRate(base : String): RevolutRate {
        return service.getCurrencyRate(base)
    }
}