package com.elnfach.arthouse.data.repository.impl

import com.elnfach.arthouse.data.repository.NewsRepository
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.Resource
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NewsRepositoryImpl : NewsRepository {
    private val _dataBase: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _dbRef: CollectionReference = _dataBase.collection("newsArticles")

    override fun addNewsArticles(newsArticle: NewsArticle, callback: (Resource<Boolean>) -> Unit) {
        newsArticle.id = _dbRef.document().id
        _dbRef.document(newsArticle.id).set(newsArticle)
            .addOnCompleteListener {
                callback(Resource.Push(data = true))
            }
            .addOnFailureListener {
                callback(Resource.Error(it.message.toString()))
            }
    }

    override fun getNewsArticles(callback: (Resource<List<NewsArticle>>) -> Unit) {
        val list: MutableList<NewsArticle> = mutableListOf()
        _dbRef.orderBy("createdAt", Query.Direction.DESCENDING).get().addOnSuccessListener { queryDocumentSnapshots ->
            if (!queryDocumentSnapshots.isEmpty) {
                queryDocumentSnapshots.documents.forEach{
                    val c = it.toObject(NewsArticle::class.java)
                    if(c != null)
                        list.add(c)
                }
            }
        }.addOnCompleteListener {
            callback(Resource.Push(list))
        }.addOnFailureListener {
            callback(Resource.Error(it.message.toString()))
        }
    }

    override fun getNewsArticleById(id: String, callback: (Resource<NewsArticle?>) -> Unit) {
        var article: NewsArticle? = null
        val dbRef = _dbRef.document(id)
        dbRef.get().addOnSuccessListener { queryDocumentSnapshots ->
            if(queryDocumentSnapshots.data != null)
            {
                val newsArticle = queryDocumentSnapshots.toObject(NewsArticle::class.java)
                article = newsArticle
            }
        }.addOnCompleteListener {
            callback(Resource.Push(article))
        }.addOnFailureListener {
            callback(Resource.Error(it.message.toString()))
        }
    }
}