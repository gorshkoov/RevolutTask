package ru.gorshkov.revoluttask.features.currencies.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.gorshkov.revoluttask.pojo.CurrencyItem

class CurrencyItemCallback(
    private val oldList: List<CurrencyItem>,
    private val newList: List<CurrencyItem>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition].currency == newList[newPosition].currency
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val old = oldList[oldPosition]
        val new = newList[newPosition]
        return old.value == new.value
    }
}