package ru.gorshkov.revoluttask.features.currencies.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.holder_currency.view.*
import ru.gorshkov.revoluttask.R
import ru.gorshkov.revoluttask.pojo.CurrencyItem
import java.util.*

class CurrencyHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val format by lazy { view.resources.getString(R.string.currency_format) }

    fun bind(item: CurrencyItem) {
        view.currency_icon.setImageResource(item.icon)
        view.currency_text.text = item.currency.name
        view.currency_name.setText(item.title)
        view.amount_edit_text.setTag(R.id.tag_currency, item)
        if (!view.amount_edit_text.isFocused) {
            if (item.value == 0.0f) {
                view.amount_edit_text.setText("")
            } else {
                view.amount_edit_text.setText(String.format(Locale.US, format, item.value))
            }
        }
    }
}