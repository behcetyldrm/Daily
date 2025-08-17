package com.example.gunluk.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gunluk.database.DailyDao
import com.example.gunluk.model.DailyModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenVM @Inject constructor(private val dao: DailyDao) : ViewModel() {

    val diaries = dao.getAllData().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun getDataById(id: Int) : Flow<DailyModel> {
        return dao.getSelectedData(id)
    }

    fun addDaily(daily: DailyModel) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertData(daily)
        }
    }

    fun updateDaily(daily: DailyModel) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.updateData(daily)
        }
    }

    fun deleteDaily(daily: DailyModel) {
        viewModelScope.launch (Dispatchers.IO){
            dao.deleteData(daily)
        }
    }
}