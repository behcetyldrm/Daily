package com.example.gunluk.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class DailyModel(
    val title: String,
    val dailyText: String,
    val date: String,
    val feel: Int,
    val today: LocalDate,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
