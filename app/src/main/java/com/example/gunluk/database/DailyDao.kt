package com.example.gunluk.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gunluk.model.DailyModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyDao {

    @Query("SELECT * FROM DailyModel")
    fun getAllData() : Flow<List<DailyModel>>

    @Query("SELECT * FROM DailyModel WHERE id = :dailyId")
    fun getSelectedData(dailyId: Int) : Flow<DailyModel>

    @Insert
    suspend fun insertData(daily: DailyModel)

    @Update
    suspend fun updateData(daily: DailyModel)

    @Delete
    suspend fun deleteData(daily: DailyModel)
}