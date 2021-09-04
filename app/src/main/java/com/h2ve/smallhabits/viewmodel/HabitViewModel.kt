package com.h2ve.smallhabits.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.h2ve.smallhabits.model.HabitResponse
import com.h2ve.smallhabits.model.ViewModelTransferObject
import com.h2ve.smallhabits.repository.HabitRepository
import com.h2ve.smallhabits.repository.ResultWrapper
import kotlinx.coroutines.launch

class HabitViewModel(private val repository: HabitRepository): ViewModel() {

    private val _allHabit = MutableLiveData<ResultWrapper<List<HabitResponse>>>()
    val allHabit: LiveData<ResultWrapper<List<HabitResponse>>> get() = _allHabit

    fun loadHabits(){
        viewModelScope.launch {
            _allHabit.value = repository.getAllHabit()
        }
    }
}