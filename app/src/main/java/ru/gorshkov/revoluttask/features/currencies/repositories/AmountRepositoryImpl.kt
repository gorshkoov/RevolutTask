package ru.gorshkov.revoluttask.features.currencies.repositories

import ru.gorshkov.revoluttask.db.dao.AmountEntityDao
import ru.gorshkov.revoluttask.db.entities.AmountEntity
import java.math.BigDecimal
import javax.inject.Inject

class AmountRepositoryImpl @Inject constructor(
    private var amountEntityDao: AmountEntityDao
) : AmountRepository {
    override suspend fun getSavedAmount(): BigDecimal? =
        amountEntityDao.getAmountValue()?.value

    override suspend fun saveAmount(value : BigDecimal) {
        amountEntityDao.insert(AmountEntity(value = value))
    }
}