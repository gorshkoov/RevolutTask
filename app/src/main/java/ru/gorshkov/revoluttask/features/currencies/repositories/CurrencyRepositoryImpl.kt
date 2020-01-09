package ru.gorshkov.revoluttask.features.currencies.repositories

import ru.gorshkov.revoluttask.db.dao.CurrencyEntityDao
import ru.gorshkov.revoluttask.db.entities.CurrencyEntity
import ru.gorshkov.revoluttask.network.CurrencyService
import ru.gorshkov.revoluttask.pojo.RevolutCurrency
import ru.gorshkov.revoluttask.utils.ConnectionUtils
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val service: CurrencyService,
    private val connectionUtils: ConnectionUtils,
    private val currencyEntityDao: CurrencyEntityDao
) : CurrencyRepository {

    private var currentBase: RevolutCurrency? = null
    private val currencyEntities: MutableList<CurrencyEntity> by lazy { getEntitiesDb() }

    override suspend fun loadCurrencies(): MutableList<CurrencyEntity> {
        if (currentBase == null) {
            currentBase = getBaseCurrency()
        }
        if (!connectionUtils.isInternetAvailable()) {
            return currencyEntities
        }
        val rate = service.getCurrencyRate(currentBase!!.name)

        for ((key, value) in rate.rates) {
            val entity = CurrencyEntity(key, value)
            val index = currencyEntities.indexOf(entity)
            if (index < 0) {
                currencyEntities.add(entity)
            } else {
                currencyEntities.remove(entity)
                currencyEntities.add(index, entity)
            }
        }

        saveToDb(currencyEntities)

        return currencyEntities
    }

    override fun updateCurrency(currency: RevolutCurrency) {
        currentBase = currency
        rearrangeList(currency)
    }

    private fun rearrangeList(currency: RevolutCurrency) {
        val item = CurrencyEntity(currency)
        val position = currencyEntities.indexOf(item)
        if (position <= 0) {
            return
        }
        currencyEntities.removeAt(position)
        currencyEntities.add(0, item)
    }

    private fun saveToDb(currencyEntities: List<CurrencyEntity>) {
        currencyEntityDao.insert(currencyEntities)
    }

    private fun getEntitiesDb(): MutableList<CurrencyEntity> = currencyEntityDao.getAll()

    private fun getBaseCurrency(): RevolutCurrency {
        return currencyEntityDao.getBaseValue()?.currency ?: RevolutCurrency.EUR
    }
}