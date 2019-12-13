package ru.gorshkov.revoluttask.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import ru.gorshkov.revoluttask.base.fragment.ViewModelFragment

inline fun <reified T : ViewModel> ViewModelFragment<T>.injectViewModel(): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}