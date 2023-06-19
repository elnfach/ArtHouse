package com.elnfach.arthouse.presentation.schedule

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elnfach.domain.models.SchoolSchedule
import com.elnfach.domain.usecase.GetSchoolScheduleUseCase
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class SchedulesScreenViewModel(
    private val getSchoolScheduleUseCase: GetSchoolScheduleUseCase
) : ViewModel()
{
    private val schoolScheduleMutableLiveData = MutableLiveData<List<SchoolSchedule>>()
    val schoolSchedule: LiveData<List<SchoolSchedule>> = schoolScheduleMutableLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    private val selectedDateMutableState = MutableLiveData(LocalDate.now())
    @RequiresApi(Build.VERSION_CODES.O)
    val selectedDate: MutableLiveData<LocalDate?> = selectedDateMutableState
    init {
        loadSchoolSchedule()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun loadSchoolSchedule()
    {
        val schoolSchedule: List<SchoolSchedule> = getSchoolScheduleUseCase.execute()
        val buffer = mutableListOf<SchoolSchedule>()
        for (item in schoolSchedule)
        {
            if (selectedDateMutableState.value?.dayOfMonth == item.date.dayOfMonth &&
                selectedDateMutableState.value?.monthValue == item.date.month &&
                selectedDateMutableState.value?.year == item.date.year)
            {
                buffer.add(item)
            }
        }
        schoolScheduleMutableLiveData.value = buffer
    }

    @RequiresApi(Build.VERSION_CODES.O)
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