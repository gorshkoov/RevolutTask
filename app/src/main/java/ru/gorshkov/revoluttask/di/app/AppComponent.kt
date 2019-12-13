package ru.gorshkov.revoluttask.di.app

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.gorshkov.revoluttask.RevolutApp
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun inject(app: RevolutApp)
}