package ru.gorshkov.revoluttask.utils

import android.view.View
import android.view.inputmethod.InputMethodManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewsUtils @Inject constructor(
    private val inputMethodManager: InputMethodManager
) {
    fun hideKeyboard(view : View) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}