package ru.gorshkov.revoluttask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.gorshkov.revoluttask.db.converter.BigDecimalConverter
import ru.gorshkov.revoluttask.db.converter.RevolutCurrencyConverter
import ru.gorshkov.revoluttask.db.dao.AmountEntityDao
import ru.gorshkov.revoluttask.db.dao.CurrencyEntityDao
import ru.gorshkov.revoluttask.db.entities.AmountEntity
import ru.gorshkov.revoluttask.db.entities.CurrencyEntity

private const val DB_VERSION = 1

@Database(entities = [CurrencyEntity::class, AmountEntity::class], version = DB_VERSION)
@TypeConverters(value = [RevolutCurrencyConverter::class, BigDecimalConverter::class])
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val CURRENCY_TABLE = "currency_table"
        const val AMOUNT_TABLE = "amount_table"
    }

    abstract fun currencyEntityDao(): CurrencyEntityDao

    abstract fun amountEntityDao(): AmountEntityDao
}