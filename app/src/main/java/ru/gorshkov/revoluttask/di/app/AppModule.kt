package ru.gorshkov.revoluttask.di.app

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import ru.gorshkov.revoluttask.MainActivity
import ru.gorshkov.revoluttask.base.coroutines.CoroutinesDispatcher
import ru.gorshkov.revoluttask.base.coroutines.DefaultCoroutinesDispatcher
import ru.gorshkov.revoluttask.di.main.MainActivityModule
import ru.gorshkov.revoluttask.di.scopes.ActivityScope
import ru.gorshkov.revoluttask.di.viewmodel.ViewModelModule

@Module(
    includes = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        DbModule::class,
        AppProvidesModule::class
    ]
)
abstract class AppModule {
    @Binds
    abstract fun bindBaseContext(app: Application): Context

    @Binds
    abstract fun bindCoroutinesDispatchers(coroutinesDispatcher: DefaultCoroutinesDispatcher): CoroutinesDispatcher

    @ContributesAndroidInjector(modules = [ViewModelModule::class, MainActivityModule::class])
    @ActivityScope
    abstract fun contributeMainActivity(): MainActivity
}