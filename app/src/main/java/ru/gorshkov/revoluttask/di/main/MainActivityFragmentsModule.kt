package ru.gorshkov.revoluttask.di.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.gorshkov.revoluttask.di.scopes.FragmentScope
import ru.gorshkov.revoluttask.features.currencies.CurrencyFragment

@Module
abstract class MainActivityFragmentsModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeCurrenciesFragment(): CurrencyFragment

}