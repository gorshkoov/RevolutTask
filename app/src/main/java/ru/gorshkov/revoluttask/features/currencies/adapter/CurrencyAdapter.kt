package ru.gorshkov.revoluttask.features.currencies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.holder_currency.view.*
import ru.gorshkov.revoluttask.R
import ru.gorshkov.revoluttask.pojo.CurrencyItem

class CurrencyAdapter(private val amountListener: CurrencyAmountListener) :
    RecyclerView.Adapter<CurrencyHolder>() {

    private var items = ArrayList<CurrencyItem>()
    private var isOffline = false

    override fun onCreateViewHolder(viewGroup: ViewGroup, layoutId: Int): CurrencyHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(layoutId, viewGroup, false)
        val holder = CurrencyHolder(view)
        holder.itemView.setOnClickListener {
            if (isOffline) {
                return@setOnClickListener
            }
            val editTextView = it.amount_edit_text
            val item = editTextView.getTag(R.id.tag_currency) as CurrencyItem
            val text = (editTextView as EditText).text.toString()
            updateCurrency(item, text)
            editTextView.requestFocus()
            editTextView.setSelection(text.length)
        }

        holder.itemView.amount_edit_text.onFocusChangeListener =
            View.OnFocusChangeListener { editTextView, hasFocus ->
                if (isOffline) {
                    return@OnFocusChangeListener
                }
                if (!hasFocus) {
                    return@OnFocusChangeListener
                }

                val item = editTextView.getTag(R.id.tag_currency) as CurrencyItem
                val text = (editTextView as EditText).text.toString()
                updateCurrency(item, text)
                editTextView.post { editTextView.setSelection(text.length) }
            }

        holder.itemView.amount_edit_text.addTextChangedListener {
            if (!holder.itemView.amount_edit_text.hasFocus()) {
                return@addTextChangedListener
            }
            val amount = it.toString()
            amountListener.onAmountChanged(amount)
        }

        return holder
    }

    override fun getItemViewType(position: Int) = R.layout.holder_currency

    override fun getItemId(position: Int): Long {
        return items[position].currency.hashCode().toLong()
    }

    fun update(newItems: MutableList<CurrencyItem>) {
        val diffResult = DiffUtil.calculateDiff(CurrencyItemCallback(this.items, newItems))
        this.items = ArrayList(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        holder.bind(items[position], isOffline, position)
    }

    private fun updateCurrency(item: CurrencyItem, amount: String) {
        val position = items.indexOf(item)
        if (position <= 0) {
            return
        }
        items.removeAt(position)
        items.add(0, item)
        notifyItemMoved(position, 0)
        amountListener.onCurrencyChanged(item.currency, amount)
    }

    fun setNetworkMode(isOffline: Boolean) {
        this.isOffline = isOffline
        notifyDataSetChanged()
    }
}