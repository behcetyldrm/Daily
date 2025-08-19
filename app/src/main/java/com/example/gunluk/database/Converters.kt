package com.example.gunluk.database

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
	@TypeConverter
	fun fromLocalDate(value: LocalDate?): String? = value?.toString()

	@TypeConverter
	fun toLocalDate(value: String?): LocalDate? = value?.let { LocalDate.parse(it) }
} 