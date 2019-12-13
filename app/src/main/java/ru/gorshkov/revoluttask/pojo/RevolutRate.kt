package ru.gorshkov.revoluttask.pojo

class RevolutRate(
    val base: String,
    val date: String,
    val rates: Map<RevolutCurrency, Float>
)