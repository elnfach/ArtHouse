package com.elnfach.arthouse.datastore.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.elnfach.arthouse.data.repository.NewsRepository
import com.elnfach.arthouse.model.LocalDate
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.Resource
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.time.delay
import org.koin.core.component.getScopeId
import java.time.Duration

class NewsRepositoryImpl : NewsRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun addNewsArticles(newsArticle: NewsArticle) : Flow<Resource<Boolean>> {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbRef: CollectionReference = db.collection("newsArticles")

        var isSuccess: Boolean = false
        var isFailed: Boolean = false

        return flow {
            emit(Resource.Loading())
            dbRef.add(newsArticle).addOnSuccessListener {
                isSuccess = true
            }.addOnFailureListener {
                isFailed = true
            }
            if(isSuccess)
            {
                emit(Resource.Success(true))
            }
            if(isFailed)
            {
                emit(Resource.Error("Fatal Error"))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getNewsArticles(): Flow<Resource<List<NewsArticle?>>> {
        val newsList: MutableList<NewsArticle?> = mutableListOf()
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbRef: CollectionReference = db.collection("newsArticles")
        return flow {
            emit(Resource.Loading())
            try {
                dbRef.get().addOnSuccessListener { queryDocumentSnapshots ->
                        if (!queryDocumentSnapshots.isEmpty) {
                            queryDocumentSnapshots.documents.forEach{
                                val c = it.toObject(NewsArticle::class.java)
                                newsList.add(c)
                            }
                        }
                    }
            } catch (e: FirebaseFirestoreException) {
                emit(Resource.Error(e.message.toString()))
            }
            delay(Duration.ofSeconds(3))
            emit(Resource.Success(newsList))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getNewsArticleById(id: String): Flow<Resource<NewsArticle?>> {
        var temp: NewsArticle? = NewsArticle()
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbRef = db.collection("newsArticles").document(id)
        return flow {
            emit(Resource.Loading())
            try {
                dbRef.get().addOnSuccessListener { queryDocumentSnapshots ->
                    val newsArticle = queryDocumentSnapshots.toObject(NewsArticle::class.java)
                    temp = newsArticle
                }
            } catch (e: FirebaseFirestoreException) {
                emit(Resource.Error(e.message.toString()))
            }
            delay(Duration.ofSeconds(3))
            emit(Resource.Success(temp))
        }
    }
}