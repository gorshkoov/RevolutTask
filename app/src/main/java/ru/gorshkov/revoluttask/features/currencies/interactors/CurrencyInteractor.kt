package ru.gorshkov.revoluttask.features.currencies.interactors

import com.github.ajalt.timberkt.Timber
import ru.gorshkov.revoluttask.features.currencies.repositories.CurrencyRepository
import ru.gorshkov.revoluttask.pojo.CurrencyItem
import ru.gorshkov.revoluttask.pojo.RevolutCurrency
import ru.gorshkov.revoluttask.utils.CurrencyUtils
import javax.inject.Inject

class CurrencyInteractor @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val currencyUtils: CurrencyUtils
) {

    private var amount : Float? = 1.0f

    suspend fun getCurrencies(base: RevolutCurrency): MutableList<CurrencyItem> {
        val rate = currencyRepository.getCurrencyRate(base.name)
        val list = ArrayList<CurrencyItem>()
        Timber.d { "Amount::getCurrencies(): $amount" }
        list.add(CurrencyItem(base, getAmount(), currencyUtils.getIcon(base),
            currencyUtils.getName(base)))

        for ((k, v) in rate.rates) {
            list.add(CurrencyItem(k, v * getAmount(), currencyUtils.getIcon(k),
                currencyUtils.getName(k)))
        }

        return list
    }

    fun updateAmount(amount: Float?) {
        this.amount = amount
    }

    private fun getAmount() : Float {
        return amount ?: 0.0f
    }
}