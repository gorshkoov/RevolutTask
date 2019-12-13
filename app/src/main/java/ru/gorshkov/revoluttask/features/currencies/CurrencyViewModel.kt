package ru.gorshkov.revoluttask.features.currencies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ajalt.timberkt.Timber
import kotlinx.coroutines.*
import ru.gorshkov.revoluttask.R
import ru.gorshkov.revoluttask.base.coroutines.CoroutinesDispatcher
import ru.gorshkov.revoluttask.features.currencies.interactors.CurrencyInteractor
import ru.gorshkov.revoluttask.pojo.CurrencyItem
import ru.gorshkov.revoluttask.pojo.RevolutCurrency
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(
    private val currencyInteractor: CurrencyInteractor,
    private val dispatchers: CoroutinesDispatcher
) : ViewModel() {

    companion object{
        const val DEFAULT_DELAY = 1000L
    }

    val errorLiveData = MutableLiveData<Int>()
    val progressLiveData = MutableLiveData<Boolean>()
    val currenciesLiveData = MutableLiveData<MutableList<CurrencyItem>>()

    private lateinit var job : Job

    private var isListInMove = false
    private var currentBaseCurrency = RevolutCurrency.EUR
    private var isPaused = false

    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.e { "CurrenciesViewModel:: $exception" }
        errorLiveData.postValue(R.string.default_error)
        progressLiveData.postValue(false)
    }

    fun onCreated() {
        progressLiveData.postValue(true)
    }

    fun onResumed() {
        isPaused = false
        reloadWithDelay()
    }

    fun onPaused() {
        isPaused = true
        job.cancel()
    }

    private fun reloadWithDelay(initialDelay: Long = 0L) {
        job = viewModelScope.launch(dispatchers.IO + handler) {
            delay(initialDelay)
            while (!isPaused) {
                isListInMove = false
                getCurrencies()
                delay(DEFAULT_DELAY)
            }
        }
    }

    private suspend fun getCurrencies() {
        val currencies = currencyInteractor.getCurrencies(currentBaseCurrency)
        if (isListInMove) {
            return
        }
        currenciesLiveData.postValue(currencies)
        progressLiveData.postValue(false)
    }

    fun onCurrencyChanged(currency: RevolutCurrency, amount: Float) {
        isListInMove = true
        this.currentBaseCurrency = currency
        currencyInteractor.updateAmount(amount)
        job.cancel()
        reloadWithDelay(DEFAULT_DELAY)
    }

    fun onAmountChanged(amount: Float?) {
        currencyInteractor.updateAmount(amount)
    }
}