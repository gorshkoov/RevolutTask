package ru.gorshkov.revoluttask.features.currencies.interactors

import ru.gorshkov.revoluttask.features.currencies.repositories.AmountRepository
import ru.gorshkov.revoluttask.features.currencies.repositories.CurrencyRepository
import ru.gorshkov.revoluttask.pojo.CurrencyItem
import ru.gorshkov.revoluttask.pojo.RevolutCurrency
import ru.gorshkov.revoluttask.utils.CurrencyUtils
import java.math.BigDecimal
import javax.inject.Inject

class CurrencyInteractor @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val amountRepository: AmountRepository,
    private val currencyUtils: CurrencyUtils
) {

    private var amount: BigDecimal? = null

    suspend fun getCurrencies(): MutableList<CurrencyItem> {
        if(amount == null) {
            amount = amountRepository.getSavedAmount() ?: BigDecimal.ONE
        }
        val currencyEntities = currencyRepository.loadCurrencies()
        val list = ArrayList<CurrencyItem>()

        for (index in currencyEntities.indices) {
            val entity = currencyEntities[index]
            val amount = if (index == 0) getAmount() else getMultiplied(entity.value)
            list.add(
                CurrencyItem(
                    entity.currency,
                    amount,
                    currencyUtils.getIcon(entity.currency),
                    currencyUtils.getName(entity.currency)
                )
            )
        }

        return list
    }

    private fun getMultiplied(strValue: String): BigDecimal {
        return BigDecimal(strValue).multiply(amount)
    }

    fun updateCurrency(currency: RevolutCurrency) {
        currencyRepository.updateCurrency(currency)
    }

    fun updateAmount(strValue: String?) {
        this.amount = if (strValue.isNullOrEmpty())
            BigDecimal.ZERO
        else {
            BigDecimal(strValue.replace(',', '.'))
        }
    }

    suspend fun storeAmountValue() {
        amountRepository.saveAmount(amount!!)
    }

    private fun getAmount(): BigDecimal {
        return amount ?: BigDecimal.ZERO
    }
}