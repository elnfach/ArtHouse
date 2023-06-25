package com.elnfach.arthouse.datastore.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.elnfach.arthouse.data.repository.ScheduleRepository
import com.elnfach.arthouse.model.LocalDate
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.model.Schedule
import com.elnfach.arthouse.model.ScheduleTask
import com.elnfach.arthouse.presentation.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.time.delay
import java.time.Duration

class ScheduleRepositoryImpl : ScheduleRepository {
    override fun saveSchoolSchedule() {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getSchoolSchedule(): Flow<Resource<List<Schedule>>> {
        val scheduleList = mutableListOf<Schedule>()
        val localDate = mutableListOf(LocalDate())
        var temp: List<Schedule>
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        return flow {
            emit(Resource.Loading())
            try {
                db.collection("schedule").get()
                    .addOnSuccessListener { queryDocumentSnapshots ->
                        if (!queryDocumentSnapshots.isEmpty) {
                            for (d in queryDocumentSnapshots) {
                                val c: Schedule = d.toObject(Schedule::class.java)
                                scheduleList.add(c)
                            }
                        }
                    }
            } catch (e: FirebaseFirestoreException) {
                emit(Resource.Error(e.message.toString()))
            }
            delay(Duration.ofSeconds(1))
            temp = scheduleList
            emit(Resource.Success(temp))
        }
    }
}