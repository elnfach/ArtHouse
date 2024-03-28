package com.elnfach.arthouse.create.schedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.elnfach.domain.repository.firebase.ScheduleRepository
import com.elnfach.model.Resource
import com.elnfach.model.Schedule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNewLessonScreenViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {
    private val _state = mutableStateOf(AddNewLessonState())
    val state: State<AddNewLessonState> = _state

    fun addNewLesson(schedule: Schedule, callback: (Boolean) -> Unit) {
        scheduleRepository.addNewLesson(schedule = schedule, callback = { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = AddNewLessonState(value = result.data)
                    if(result.data != null)
                        callback(result.data!!)
                }
                is Resource.Error -> {
                    _state.value = AddNewLessonState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = AddNewLessonState(loading = true)
                }
            }

        })
    }
}