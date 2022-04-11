package com.compose.stockmarket.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StockEntity::class], version = 1)

abstract class StockDatabase: RoomDatabase() {

    abstract fun dao() : StockDao

}