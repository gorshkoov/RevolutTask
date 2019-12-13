package ru.gorshkov.revoluttask.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewsUtils @Inject constructor() {
    fun hideKeyboard(view : View) {
        val imm =
            view.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}