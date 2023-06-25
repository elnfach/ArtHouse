package com.elnfach.arthouse.datastore.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.elnfach.arthouse.data.repository.NewsRepository
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.time.delay
import java.io.IOException
import java.time.Duration
import javax.security.auth.callback.Callback

class NewsRepositoryImpl : NewsRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getNewsArticles(): Flow<Resource<List<NewsArticle?>>> {
        val newsList: MutableList<NewsArticle?> = mutableListOf()
        var temp: List<NewsArticle?>
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        return flow {
            emit(Resource.Loading())
            try {
                db.collection("newsArticles").get()
                    .addOnSuccessListener { queryDocumentSnapshots ->
                        if (!queryDocumentSnapshots.isEmpty) {
                            val list = queryDocumentSnapshots.documents
                            for (d in list) {
                                val c: NewsArticle? = d.toObject(NewsArticle::class.java)
                                newsList.add(c)
                            }
                        }
                    }
            } catch (e: FirebaseFirestoreException) {
                emit(Resource.Error(e.message.toString()))
            }
            delay(Duration.ofSeconds(3))
            temp = newsList
            emit(Resource.Success(temp))
        }
        /*return flow{
            emit(Resource.Loading())
            db.collection("newsArticles").get()
                .addOnSuccessListener {
                    ...
                }
            delay(Duration.ofSeconds(3))
            temp = newsList
            emit(Resource.Success(temp))
        }.catch {
            ...
        }*/
    }
}