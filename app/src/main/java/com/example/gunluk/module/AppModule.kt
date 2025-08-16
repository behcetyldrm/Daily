package com.example.gunluk.module

import android.content.Context
import androidx.room.Room
import com.example.gunluk.database.DailyDao
import com.example.gunluk.database.DailyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : DailyDatabase {
        return Room.databaseBuilder(
            context,
            DailyDatabase::class.java,
            "daily_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDailyDao(db: DailyDatabase) : DailyDao {
        return db.dailyDao()
    }
}