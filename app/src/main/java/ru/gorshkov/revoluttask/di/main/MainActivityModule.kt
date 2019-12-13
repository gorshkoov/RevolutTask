package ru.gorshkov.revoluttask.di.main

import dagger.Binds
import dagger.Module
import ru.gorshkov.revoluttask.MainActivity
import ru.gorshkov.revoluttask.base.activity.BaseActivity
import ru.gorshkov.revoluttask.features.currencies.repositories.CurrencyRepository
import ru.gorshkov.revoluttask.features.currencies.repositories.CurrencyRepositoryImpl

@Module(includes = [MainActivityFragmentsModule::class])
abstract class MainActivityModule {
    @Binds
    abstract fun bindBaseActivity(activity: MainActivity): BaseActivity

    @Binds
    abstract fun bindCurrencyRepository(currencyRepositoryImpl: CurrencyRepositoryImpl) : CurrencyRepository
}