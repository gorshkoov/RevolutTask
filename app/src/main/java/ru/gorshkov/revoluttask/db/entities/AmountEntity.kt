package ru.gorshkov.revoluttask.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.gorshkov.revoluttask.db.AppDatabase.Companion.AMOUNT_TABLE
import java.math.BigDecimal

@Entity(tableName = AMOUNT_TABLE)
data class AmountEntity(
    @PrimaryKey
    val id: Int = 1,
    var value : BigDecimal
)