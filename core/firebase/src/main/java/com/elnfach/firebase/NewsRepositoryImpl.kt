package com.elnfach.firebase

import android.util.Log
import com.elnfach.domain.repository.firebase.NewsRepository
import com.elnfach.model.NewsArticle
import com.elnfach.model.Resource
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NewsRepositoryImpl : NewsRepository {
    private val _database: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _databaseReference: CollectionReference = _database.collection("newsArticles")

    override fun addNewsArticles(newsArticle: NewsArticle, callback: (Resource<Boolean>) -> Unit) {
        newsArticle.id = _databaseReference.document().id
        val article = com.elnfach.firebase.model.NewsArticle(
            image = newsArticle.image,
            title = newsArticle.title,
            createdAt = Timestamp.now(),
            content = newsArticle.content,
            authors = newsArticle.authors,
            gallery = newsArticle.gallery,
            id = newsArticle.id
        )
        _databaseReference.document(article.id).set(article).addOnCompleteListener {
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
                    val newsArticle = it.toObject(com.elnfach.firebase.model.NewsArticle::class.java)
                    if (newsArticle != null) {
                        Log.e("EEE", "3")
                        list.add(NewsArticle(
                            image = newsArticle.image,
                            title = newsArticle.title,
                            createdAt = newsArticle.createdAt.seconds,
                            content = newsArticle.content,
                            authors = newsArticle.authors,
                            gallery = newsArticle.gallery,
                            id = newsArticle.id
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
                val newsArticle = task.toObject(com.elnfach.firebase.model.NewsArticle::class.java)
                if (newsArticle != null) {
                    article = NewsArticle(
                        image = newsArticle.image,
                        title = newsArticle.title,
                        createdAt = newsArticle.createdAt.seconds,
                        content = newsArticle.content,
                        authors = newsArticle.authors,
                        gallery = newsArticle.gallery,
                        id = newsArticle.id
                    )
                }
            }
        }.addOnCompleteListener {
            callback(Resource.Push(article))
        }.addOnFailureListener {
            callback(Resource.Error(it.message.toString()))
        }
    }
}