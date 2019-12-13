package ru.gorshkov.revoluttask.base.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.gorshkov.revoluttask.base.coroutines.CoroutinesDispatcher
import javax.inject.Inject

class DefaultCoroutinesDispatcher @Inject constructor(): CoroutinesDispatcher {
    override val Main: CoroutineDispatcher = Dispatchers.Main
    override val IO: CoroutineDispatcher = Dispatchers.IO
    override val Computation: CoroutineDispatcher = Dispatchers.Default
}