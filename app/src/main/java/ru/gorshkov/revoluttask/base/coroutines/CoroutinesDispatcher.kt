package ru.gorshkov.revoluttask.base.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutinesDispatcher {
    val Main: CoroutineDispatcher
    val IO: CoroutineDispatcher
    val Computation: CoroutineDispatcher
}