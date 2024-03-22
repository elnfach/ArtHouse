package com.elnfach.arthouse.presentation.schedule

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elnfach.domain.usecase.GetScheduleStateUseCase
import com.elnfach.domain.usecase.GetScheduleUseCase
import com.elnfach.domain.usecase.SaveScheduleStateUseCase
import com.elnfach.model.LocalDate
import com.elnfach.model.Resource
import com.elnfach.model.Schedule

class ScheduleScreenViewModel(
    private val saveScheduleStateUseCase: SaveScheduleStateUseCase,
    private val getScheduleStateUseCase: GetScheduleStateUseCase,
    private val getSchoolScheduleUseCase: GetScheduleUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ScheduleState())
    val state: State<ScheduleState> = _state

    private val selectedDateMutableState = MutableLiveData(getScheduleStateUseCase.invoke())
    val selectedDate: MutableLiveData<LocalDate?> = selectedDateMutableState
    init {
        loadSchedule()
    }

    fun loadSchedule()
    {
        getSchoolScheduleUseCase(callback = { result ->
            when(result){
                is Resource.Push -> {
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
                is Resource.Error -> {
                    _state.value = ScheduleState(error = result.message ?: "An unexpected error occurred")
                }
            }
        })
    }

    fun saveDate(date: LocalDate)
    {
        saveScheduleStateUseCase.invoke(date)
        selectedDateMutableState.value = date
    }

    fun loadDate() : MutableLiveData<LocalDate?>
    {
        return selectedDate
    }

    fun pullToRefresh()
    {
        loadSchedule()
    }
}