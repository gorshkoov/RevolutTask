package ru.gorshkov.revoluttask.pojo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.math.BigDecimal

data class CurrencyItem(
    val currency: RevolutCurrency,
    val value: BigDecimal,
    @DrawableRes val icon : Int,
    @StringRes val title : Int
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CurrencyItem

        if (currency != other.currency) return false
        if (icon != other.icon) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = currency.hashCode()
        result = 31 * result + icon
        result = 31 * result + title
        return result
    }
}