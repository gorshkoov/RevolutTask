package ru.gorshkov.revoluttask.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.gorshkov.revoluttask.db.entities.CurrencyEntity

@Dao
interface CurrencyEntityDao {
    @Query("SELECT * FROM currency_table")
    fun getAll(): MutableList<CurrencyEntity>

    @Query("SELECT * FROM currency_table LIMIT 1")
    fun getBaseValue() : CurrencyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(values : List<CurrencyEntity>)
}