package ru.gorshkov.revoluttask.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.gorshkov.revoluttask.di.scopes.ActivityScope
import ru.gorshkov.revoluttask.features.currencies.CurrencyViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    internal abstract fun provideCurrenciesViewModel(photoViewModel: CurrencyViewModel): ViewModel

    @Binds
    @ActivityScope
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}