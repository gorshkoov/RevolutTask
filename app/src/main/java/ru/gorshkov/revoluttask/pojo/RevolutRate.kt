package ru.gorshkov.revoluttask.pojo


data class RevolutRate(
    val base: String,
    val date: String,
    val rates: Map<RevolutCurrency, Float>
)