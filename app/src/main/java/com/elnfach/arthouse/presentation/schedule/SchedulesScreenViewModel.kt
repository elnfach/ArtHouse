package com.elnfach.arthouse.presentation.schedule

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elnfach.domain.models.SchoolSchedule
import com.elnfach.domain.usecase.GetSchoolScheduleUseCase

class SchedulesScreenViewModel(
    private val getSchoolScheduleUseCase: GetSchoolScheduleUseCase
) : ViewModel()
{
    private val schoolScheduleMutableLiveData = MutableLiveData<List<SchoolSchedule>>()
    val schoolSchedule: LiveData<List<SchoolSchedule>> = schoolScheduleMutableLiveData

    private var state: MutableState<Int> = mutableIntStateOf(0)
    init {
        loadSchoolSchedule()
    }
    @Composable
    fun SaveId(id: Int)
    {
        state = remember { mutableIntStateOf(id) }
    }
    private fun loadSchoolSchedule()
    {
        val schoolSchedule: List<SchoolSchedule> = getSchoolScheduleUseCase.execute()
        schoolScheduleMutableLiveData.value = schoolSchedule
    }
    override fun onCleared() {
        super.onCleared()
    }
}