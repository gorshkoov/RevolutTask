package ru.gorshkov.revoluttask.features.currencies.adapter

import ru.gorshkov.revoluttask.pojo.RevolutCurrency


interface CurrencyAmountListener {
    fun onCurrencyChanged(currency: RevolutCurrency, amount: String)

    fun onAmountChanged(amount: String?)
}