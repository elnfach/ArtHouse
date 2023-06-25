package com.elnfach.arthouse.presentation.schedule

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elnfach.arthouse.domain.usecase.GetScheduleUseCase
import com.elnfach.arthouse.model.LocalDate
import com.elnfach.arthouse.model.Schedule
import com.elnfach.arthouse.presentation.utils.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ScheduleScreenViewModel(
    private val getSchoolScheduleUseCase: GetScheduleUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ScheduleState())
    val state: State<ScheduleState> = _state

    private val selectedDateMutableState = MutableLiveData(LocalDate())
    val selectedDate: MutableLiveData<LocalDate?> = selectedDateMutableState
    init {
        loadSchoolSchedule()
    }
    fun loadSchoolSchedule()
    {
        getSchoolScheduleUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = ScheduleState(schedule = result.data ?: emptyList())
                    val buffer = mutableListOf<Schedule>()
                    for (item in _state.value.schedule)
                    {
                        Log.e("state.schedule", item.date.dayOfMonth.toString())
                        if (selectedDateMutableState.value?.dayOfMonth == item.date.dayOfMonth &&
                            selectedDateMutableState.value?.monthNumber == item.date.monthNumber &&
                            selectedDateMutableState.value?.year == item.date.year)
                        {
                            Log.e("state.schedule", item.date.dayOfMonth.toString())
                            buffer.add(item)
                        }
                    }
                    _state.value.schedule = buffer
                }
                is Resource.Loading -> {
                    _state.value = ScheduleState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = ScheduleState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun saveDate(date: LocalDate)
    {
        selectedDateMutableState.value = date
    }

    fun loadDate() : MutableLiveData<LocalDate?>
    {
        return selectedDate
    }
    override fun onCleared() {
        super.onCleared()
    }
}