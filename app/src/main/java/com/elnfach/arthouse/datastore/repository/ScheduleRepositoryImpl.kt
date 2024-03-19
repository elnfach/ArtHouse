package com.elnfach.arthouse.datastore.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.elnfach.arthouse.data.repository.ScheduleRepository
import com.elnfach.arthouse.model.LocalDate
import com.elnfach.arthouse.model.Schedule
import com.elnfach.arthouse.presentation.utils.Resource
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.time.delay
import java.time.Duration

class ScheduleRepositoryImpl : ScheduleRepository {
    private val _dataBase: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _dbRef: CollectionReference = _dataBase.collection("schedule")
    override fun saveSchoolSchedule() {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getSchoolSchedule(): Flow<Resource<List<Schedule>>> {
        val scheduleList = mutableListOf<Schedule>()
        var temp: List<Schedule>

        return flow {
            emit(Resource.Loading())
            try {
                _dbRef.get()
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
            delay(Duration.ofMillis(1500))
            temp = scheduleList
            emit(Resource.Success(temp))
        }
    }
}