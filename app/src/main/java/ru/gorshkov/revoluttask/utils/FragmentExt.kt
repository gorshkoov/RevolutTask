package ru.gorshkov.revoluttask.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import ru.gorshkov.revoluttask.base.fragment.ViewModelFragment

inline fun <reified T : ViewModel> ViewModelFragment<T>.injectViewModel(): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

fun Fragment.toast(message : Int) {
    Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
}