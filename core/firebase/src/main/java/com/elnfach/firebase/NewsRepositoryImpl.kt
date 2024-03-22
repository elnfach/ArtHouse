package com.elnfach.firebase

import android.util.Log
import com.elnfach.domain.repository.firebase.NewsRepository
import com.elnfach.model.NewsArticle
import com.elnfach.model.Resource
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NewsRepositoryImpl : NewsRepository {
    private val _database: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _databaseReference: CollectionReference = _database.collection("newsArticles")

    override fun addNewsArticles(newsArticle: NewsArticle, callback: (Resource<Boolean>) -> Unit) {
        newsArticle.id = _databaseReference.document().id
        _databaseReference.document(newsArticle.id).set(newsArticle).addOnCompleteListener {
            callback(Resource.Push(data = true))
        }.addOnFailureListener {
            callback(Resource.Error(it.message.toString()))
        }
    }

    override fun getNewsArticles(callback: (Resource<List<NewsArticle>>) -> Unit) {
        val list: MutableList<NewsArticle> = mutableListOf()
        Log.e("EEE", "1")
        _databaseReference.orderBy("createdAt", Query.Direction.DESCENDING).get().addOnSuccessListener { task ->
            Log.e("EEE", "2")
            if (!task.isEmpty) {
                task.documents.forEach{
                    val c = it.toObject(com.elnfach.firebase.model.NewsArticle::class.java)
                    if (c != null) {
                        Log.e("EEE", "3")
                        list.add(NewsArticle(
                            image = c.image,
                            title = c.title,
                            createdAt = c.createdAt.seconds,
                            content = c.content,
                            authors = c.authors,
                            gallery = c.gallery,
                            id = c.id
                        ))
                    }
                }
            }
        }.addOnCompleteListener {
            Log.e("EEE", "4")
            callback(Resource.Push(list))
        }.addOnFailureListener {
            callback(Resource.Error(it.message.toString()))
        }
    }

    override fun getNewsArticleById(id: String, callback: (Resource<NewsArticle?>) -> Unit) {
        var article: NewsArticle? = null
        val dbRef = _databaseReference.document(id)
        dbRef.get().addOnSuccessListener { task ->
            if(task.data != null)
            {
                val newsArticle = task.toObject(NewsArticle::class.java)
                article = newsArticle
            }
        }.addOnCompleteListener {
            callback(Resource.Push(article))
        }.addOnFailureListener {
            callback(Resource.Error(it.message.toString()))
        }
    }
}