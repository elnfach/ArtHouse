package com.elnfach.data.storage

import com.elnfach.data.R
import com.elnfach.data.storage.models.Article

class NewsArticlesStorage() : NewsArticleStorage
{
    override fun save() {
    }

    override fun get(): List<Article> {
        return listOf(
            Article(0, R.drawable.picture1,"Здесь должен быть заголовок статьи 0", "Содержание статьи 0"),
            Article(1, R.drawable.picture1,"Здесь должен быть заголовок статьи 1", "Содержание статьи 1"),
            Article(2, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи 2"),
            Article(3, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи 3 "),
            Article(4, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи4 "),
            Article(5, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи 5"),
            Article(6, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи6 "),
            Article(7, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи7 "),
            Article(8, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи 8"),
            Article(9, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи 9"),
            Article(10, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи 10"),
            Article(11, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи 11"),
            Article(12, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи 12"),
            Article(13, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(14, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(15, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(16, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(17, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(18, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
            Article(19, R.drawable.picture1,"Здесь должен быть заголовок статьи", "Содержание статьи"),
        )
    }

}