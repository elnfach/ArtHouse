package com.elnfach.firebase

import com.elnfach.firebase.repository.NewsRepository
import com.elnfach.model.Resource
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NewsRepositoryImpl : NewsRepository {
    private val _database: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _databaseReference: CollectionReference = _database.collection("newsArticles")

    override fun addNewsArticles(newsArticle: com.elnfach.model.NewsArticle, callback: (Resource<Boolean>) -> Unit) {
        newsArticle.id = _databaseReference.document().id
        _databaseReference.document(newsArticle.id).set(newsArticle)
            .addOnCompleteListener {
                callback(Resource.Push(data = true))
            }.addOnFailureListener {
                callback(Resource.Error(it.message.toString()))
            }
    }

    override fun getNewsArticles(callback: (Resource<List<com.elnfach.model.NewsArticle>>) -> Unit) {
        val list: MutableList<com.elnfach.model.NewsArticle> = mutableListOf()
        _databaseReference.orderBy("createdAt", Query.Direction.DESCENDING).get().addOnSuccessListener { queryDocumentSnapshots ->
            if (!queryDocumentSnapshots.isEmpty) {
                queryDocumentSnapshots.documents.forEach{
                    val c = it.toObject(com.elnfach.model.NewsArticle::class.java)
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

    override fun getNewsArticleById(id: String, callback: (Resource<com.elnfach.model.NewsArticle?>) -> Unit) {
        var article: com.elnfach.model.NewsArticle? = null
        val dbRef = _databaseReference.document(id)
        dbRef.get().addOnSuccessListener { queryDocumentSnapshots ->
            if(queryDocumentSnapshots.data != null)
            {
                val newsArticle = queryDocumentSnapshots.toObject(com.elnfach.model.NewsArticle::class.java)
                article = newsArticle
            }
        }.addOnCompleteListener {
            callback(Resource.Push(article))
        }.addOnFailureListener {
            callback(Resource.Error(it.message.toString()))
        }
    }
}