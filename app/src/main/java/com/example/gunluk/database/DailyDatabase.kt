package com.example.gunluk.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gunluk.model.DailyModel

@Database(entities = [DailyModel::class], version = 2)
@TypeConverters(Converters::class)
abstract class DailyDatabase : RoomDatabase() {
    abstract fun dailyDao(): DailyDao
}