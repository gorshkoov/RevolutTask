package ru.gorshkov.revoluttask.di.app

import android.content.Context
import android.view.inputmethod.InputMethodManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppProvidesModule {
    @Provides
    @Singleton
    fun providesInputMethodManager(context: Context) : InputMethodManager {
        return context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
}