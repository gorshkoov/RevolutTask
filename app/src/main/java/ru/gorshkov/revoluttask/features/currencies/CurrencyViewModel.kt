package ru.gorshkov.revoluttask.features.currencies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ajalt.timberkt.Timber
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.gorshkov.revoluttask.base.coroutines.CoroutinesDispatcher
import ru.gorshkov.revoluttask.features.currencies.interactors.CurrencyInteractor
import ru.gorshkov.revoluttask.pojo.CurrencyItem
import ru.gorshkov.revoluttask.pojo.RevolutCurrency
import ru.gorshkov.revoluttask.utils.ConnectionUtils
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(
    private val currencyInteractor: CurrencyInteractor,
    private val dispatchers: CoroutinesDispatcher,
    private val connectionUtils: ConnectionUtils
) : ViewModel() {

    companion object {
        const val DEFAULT_DELAY = 1000L
    }

    val progressLiveData = MutableLiveData<Boolean>()
    val currenciesLiveData = MutableLiveData<MutableList<CurrencyItem>>()
    val offlineChangedLiveData = MutableLiveData<Boolean>()

    private lateinit var job: Job

    private var isListInMove = false
    private var isPaused = false

    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception) { "CurrencyViewModel::handler" }
        progressLiveData.postValue(false)
        //reload currencies without internet
        if (exception is ConnectException || exception is UnknownHostException) {
            reloadWithDelay()
        }
    }

    fun onCreated() {
        connectionUtils.subscribeToConnectionChanges { offlineChangedLiveData.postValue(it) }
        offlineChangedLiveData.postValue(!connectionUtils.isInternetAvailable())
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
        val currencies = currencyInteractor.getCurrencies()
        if (isListInMove) {
            return
        }
        currenciesLiveData.postValue(currencies)
        progressLiveData.postValue(false)
    }

    fun onCurrencyChanged(currency: RevolutCurrency, amount: String) {
        isListInMove = true
        currencyInteractor.updateCurrency(currency)
        updateAmount(amount)
        job.cancel()
        reloadWithDelay(DEFAULT_DELAY)
    }

    fun onAmountChanged(amount: String?) {
        updateAmount(amount)
    }

    private fun updateAmount(amount: String?) {
        currencyInteractor.updateAmount(amount)
        viewModelScope.launch(dispatchers.IO) { currencyInteractor.storeAmountValue() }
    }
}