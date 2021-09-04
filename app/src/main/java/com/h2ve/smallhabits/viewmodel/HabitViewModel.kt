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

    private val _allHabit = MutableLiveData<ViewModelTransferObject<List<HabitResponse>>>()
    val allHabit: LiveData<ViewModelTransferObject<List<HabitResponse>>> get() = _allHabit

    fun loadHabits(){
        viewModelScope.launch {
            when(val habitResponse = repository.getAllHabit()){
                is ResultWrapper.GenericError ->{
                    _allHabit.value = ViewModelTransferObject.GenericError(habitResponse.error)
                    _allHabit.postValue(ViewModelTransferObject.GenericError(habitResponse.error))
                }
                is ResultWrapper.Success -> {
                    _allHabit.value = ViewModelTransferObject.Success(habitResponse.value)
                    _allHabit.postValue(ViewModelTransferObject.Success(habitResponse.value))
                }
                is ResultWrapper.NetworkError -> {
                    _allHabit.value = ViewModelTransferObject.NetworkError
                    _allHabit.postValue(ViewModelTransferObject.NetworkError)
                }
            }
        }
    }
}