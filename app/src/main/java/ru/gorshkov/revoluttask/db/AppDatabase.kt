package ru.gorshkov.revoluttask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.gorshkov.revoluttask.db.converter.RevolutCurrencyConverter
import ru.gorshkov.revoluttask.db.entities.CurrencyEntity

private const val DB_VERSION = 1

@Database(entities = [CurrencyEntity::class], version = DB_VERSION)
@TypeConverters(value = [RevolutCurrencyConverter::class])
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val CURRENCY_TABLE = "currency_table"
    }
    abstract fun currencyEntityDao(): CurrencyEntityDao
}