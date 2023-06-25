package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.ScheduleRepository
import com.elnfach.arthouse.model.Schedule
import com.elnfach.arthouse.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetScheduleUseCase(
    private val scheduleRepository: ScheduleRepository
) {
    operator fun invoke(): Flow<Resource<List<Schedule>>> {
        return scheduleRepository.getSchoolSchedule()
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