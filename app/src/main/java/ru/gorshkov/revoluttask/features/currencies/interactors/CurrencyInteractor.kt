package ru.gorshkov.revoluttask.features.currencies.interactors

import com.github.ajalt.timberkt.Timber
import ru.gorshkov.revoluttask.features.currencies.repositories.CurrencyRepository
import ru.gorshkov.revoluttask.pojo.CurrencyItem
import ru.gorshkov.revoluttask.pojo.RevolutCurrency
import ru.gorshkov.revoluttask.utils.CurrencyUtils
import java.math.BigDecimal
import javax.inject.Inject

class CurrencyInteractor @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val currencyUtils: CurrencyUtils
) {

    private var amount: BigDecimal? = BigDecimal.ONE

    suspend fun getCurrencies(base: RevolutCurrency): MutableList<CurrencyItem> {
        val rate = currencyRepository.getCurrencyRate(base.name)
        val list = ArrayList<CurrencyItem>()
        Timber.d { "Amount::getCurrencies(): $amount" }
        list.add(
            CurrencyItem(
                base, getAmount(), currencyUtils.getIcon(base),
                currencyUtils.getName(base)
            )
        )

        for ((currency, amount) in rate.rates) {
            list.add(
                CurrencyItem(
                    currency, getMultiplied(amount), currencyUtils.getIcon(currency),
                    currencyUtils.getName(currency)
                )
            )
        }

        return list
    }

    private fun getMultiplied(strValue: String): BigDecimal {
        return BigDecimal(strValue).multiply(amount)
    }

    fun updateAmount(strValue: String?) {
        this.amount = if (strValue.isNullOrEmpty())
            BigDecimal.ZERO
        else {
            BigDecimal(strValue.replace(',', '.'))
        }
    }

    private fun getAmount(): BigDecimal {
        return amount ?: BigDecimal.ZERO
    }
}