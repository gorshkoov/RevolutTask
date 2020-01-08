package ru.gorshkov.revoluttask.di.app

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import ru.gorshkov.revoluttask.db.AppDatabase
import ru.gorshkov.revoluttask.db.CurrencyEntityDao
import javax.inject.Singleton

private const val DB_NAME = "revolut_db"

@Module
class DbModule {
    @Provides
    @Singleton
    fun providesDb(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()

    @Provides
    @Singleton
    fun providesCurrencyEntityDao(db: AppDatabase): CurrencyEntityDao = db.currencyEntityDao()

}