package com.example.gunluk.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailyModel(
    val title: String,
    val dailyText: String,
    val date: String,
    val feel: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
