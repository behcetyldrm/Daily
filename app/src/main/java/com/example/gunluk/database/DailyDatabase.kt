package com.example.gunluk.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gunluk.model.DailyModel

@Database(entities = [DailyModel::class], version = 1)
abstract class DailyDatabase : RoomDatabase() {
    abstract fun dailyDao(): DailyDao
}