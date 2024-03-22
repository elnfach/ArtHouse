package com.elnfach.domain.usecase

import com.elnfach.domain.repository.firebase.ScheduleRepository
import com.elnfach.model.Resource
import com.elnfach.model.Schedule

class GetScheduleUseCase(
    private val scheduleRepository: ScheduleRepository
) {
    operator fun invoke(callback: (Resource<List<Schedule>>) -> Unit) {
        return scheduleRepository.getSchoolSchedule(callback)
    }

    /*private fun quicksort(items: Flow<Resource<List<Schedule>>>): Flow<Resource<List<Schedule>>> {
        items.transform { it ->
            if ((it.data?.count() ?: 0) < 2){
                emit(it.data)
            }
            val middle = it.data?.get(items.count() / 2)
            val equal = it.data?.filter { it == middle }
            val less = it.data?.filter { it.grade < (middle?.grade ?: 0) }
            val greater = it.data?.filter { it.grade > (middle?.grade ?: 0) }
            emit(items(less) + equal + quicksort(greater))
        }
        return items
    }*/
}