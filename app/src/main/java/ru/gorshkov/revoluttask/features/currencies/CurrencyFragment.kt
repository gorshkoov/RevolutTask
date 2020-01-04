package ru.gorshkov.revoluttask.features.currencies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import kotlinx.android.synthetic.main.fragment_currencies.*
import ru.gorshkov.revoluttask.R
import ru.gorshkov.revoluttask.base.fragment.ViewModelFragment
import ru.gorshkov.revoluttask.features.currencies.adapter.CurrencyAdapter
import ru.gorshkov.revoluttask.features.currencies.adapter.CurrencyAmountListener
import ru.gorshkov.revoluttask.pojo.RevolutCurrency
import ru.gorshkov.revoluttask.utils.ViewsUtils
import ru.gorshkov.revoluttask.utils.injectViewModel
import javax.inject.Inject

class CurrencyFragment : ViewModelFragment<CurrencyViewModel>(), CurrencyAmountListener {
    @Inject
    lateinit var viewsUtils: ViewsUtils

    override fun initViewModel(): CurrencyViewModel = injectViewModel()

    override val layoutRes = R.layout.fragment_currencies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.currenciesLiveData.observe(this, Observer {
            (currencies_recycler.adapter as CurrencyAdapter).update(it)
        })
        viewModel.progressLiveData.observe(this, Observer {
            progress_view.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
        viewModel.onCreated()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(currencies_recycler) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
            val currencyAdapter = CurrencyAdapter(this@CurrencyFragment)
            currencyAdapter.setHasStableIds(true)
            adapter = currencyAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisiblePosition: Int = layoutManager.findFirstVisibleItemPosition()
                    if (firstVisiblePosition > 0) {
                        viewsUtils.hideKeyboard(recyclerView)
                    }
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResumed()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPaused()
    }

    override fun onCurrencyChanged(currency: RevolutCurrency, amount: Float) {
        viewModel.onCurrencyChanged(currency, amount)
    }

    override fun onAmountChanged(amount: Float?) {
        viewModel.onAmountChanged(amount)
    }
}