package com.elnfach.arthouse.schedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elnfach.domain.usecase.GetScheduleStateUseCase
import com.elnfach.domain.usecase.GetScheduleUseCase
import com.elnfach.domain.usecase.SaveScheduleStateUseCase
import com.elnfach.model.Resource
import com.elnfach.model.Schedule
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ScheduleScreenViewModel @Inject constructor(
    private val saveScheduleStateUseCase: SaveScheduleStateUseCase,
    private val getScheduleStateUseCase: GetScheduleStateUseCase,
    private val getSchoolScheduleUseCase: GetScheduleUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ScheduleState())
    val state: State<ScheduleState> = _state

    private val selectedDateMutableState = MutableLiveData(LocalDate.now())
    val selectedDate: MutableLiveData<LocalDate?> = selectedDateMutableState
    init {
        loadSchedule()
    }

    fun loadSchedule()
    {
        getSchoolScheduleUseCase(callback = { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = ScheduleState(value = result.data ?: emptyList())
                    val temp = mutableListOf<Schedule>()
                    for (item in _state.value.value)
                    {
                        if (selectedDateMutableState.value?.dayOfMonth == item.date.dayOfMonth &&
                            selectedDateMutableState.value?.monthValue == item.date.monthValue &&
                            selectedDateMutableState.value?.year == item.date.year)
                        {
                            temp.add(item)
                        }
                    }
                    _state.value.value = temp
                }
                is Resource.Error -> {
                    _state.value = ScheduleState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = ScheduleState(loading = true)
                }
            }
        })
    }

    fun saveDate(date: LocalDate)
    {
        selectedDateMutableState.value = date
    }

    fun pullToRefresh()
    {
        loadSchedule()
    }
}