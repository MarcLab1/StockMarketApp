package com.compose.stockmarket.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {

    @Query("SELECT * from stocks")
    fun getStocks() : Flow<List<StockEntity>>

    @Query("SELECT * from stocks where name =:name")
    fun searchStocks(name : String) : Flow<List<StockEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertStock(stockEntity: StockEntity)

    @Update()
    suspend fun updateStock(stockEntity: StockEntity)

    @Delete
    suspend fun deleteStock(stockEntity: StockEntity)

    @Query("Delete from stocks")
    fun deleteAllStocks()
}