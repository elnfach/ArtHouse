package com.elnfach.data.storage

import com.elnfach.data.R
import com.elnfach.data.storage.models.Article

class NewsArticlesStorage() : NewsArticleStorage
{
    override fun save() {
    }

    override fun get(): List<Article> {
        return listOf(
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
        )
    }

}