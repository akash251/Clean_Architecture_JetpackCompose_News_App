package com.kamatiaakash.compose_news_app.data.mapper

import com.kamatiaakash.compose_news_app.data.remote.dto.ArticleDto
import com.kamatiaakash.compose_news_app.data.remote.dto.NewsDto
import com.kamatiaakash.compose_news_app.domain.model.Article
import com.kamatiaakash.compose_news_app.domain.model.News

fun ArticleDto.toArticle():Article{
    return Article(
        author = author ?: "",
        publishedAt =  "" ,
        content = content ?: "",
        urlToImage = urlToImage ?: "" ,
        url = url ?: "",
        title = title?: ""  ,
        description = description?: ""
    )
}


fun NewsDto.toNews(): News {
    val articles = articles?.map { it.toArticle() }
    return News(
        article = articles ?: emptyList()
    )
}