package ru.gorshkov.revoluttask.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.gorshkov.revoluttask.pojo.RevolutRate

interface CurrencyService {
    @GET("latest")
    suspend fun getCurrencyRate(@Query("base") baseCurrency: String) : RevolutRate
}