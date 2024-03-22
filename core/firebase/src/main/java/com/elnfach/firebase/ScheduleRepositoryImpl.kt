package com.elnfach.firebase

import com.elnfach.domain.repository.firebase.ScheduleRepository
import com.elnfach.model.Resource
import com.elnfach.model.Schedule
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class ScheduleRepositoryImpl : ScheduleRepository {
    private val _database: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _databaseReference: CollectionReference = _database.collection("schedule")

    override fun saveSchoolSchedule() {

    }

    override fun getSchoolSchedule(callback: (Resource<List<Schedule>>) -> Unit) {
        val scheduleList = mutableListOf<Schedule>()

        _databaseReference.get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    for (d in queryDocumentSnapshots) {
                        val c: Schedule = d.toObject(Schedule::class.java)
                        scheduleList.add(c)
                    }
                }
            }.addOnCompleteListener {
                callback(Resource.Push(scheduleList))
            }
            .addOnFailureListener {
                callback(Resource.Error(it.message.toString()))
            }

    }
}