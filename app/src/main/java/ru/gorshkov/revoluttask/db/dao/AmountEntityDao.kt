package ru.gorshkov.revoluttask.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.gorshkov.revoluttask.db.entities.AmountEntity
import ru.gorshkov.revoluttask.db.entities.CurrencyEntity

@Dao
interface AmountEntityDao {
    @Query("SELECT * FROM amount_table LIMIT 1")
    fun getAmountValue() : AmountEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value: AmountEntity)
}