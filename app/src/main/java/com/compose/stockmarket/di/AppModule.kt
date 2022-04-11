package com.compose.stockmarket.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.compose.stockmarket.database.StockDatabase
import com.compose.stockmarket.network.ApiService
import com.compose.stockmarket.repository.ProdStockRepository
import com.compose.stockmarket.repository.StockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.compose.stockmarket.util.Constants as Constants1

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideString() = "jsdfjfsj"

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants1.BASE_URL).build().create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideStockRepository(apiService: ApiService): StockRepository {
        return ProdStockRepository(apiService = apiService)
    }

    @Singleton
    @Provides
    fun provideStockDatabase(application: Application): StockDatabase {
        return Room.databaseBuilder(application, StockDatabase::class.java, "stocks_db")
            .fallbackToDestructiveMigration().build()
    }
}