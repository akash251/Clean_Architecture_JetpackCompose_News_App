package com.kamatiaakash.compose_news_app.data.remote.dto

data class NewsDto(
    val articles: List<ArticleDto>? = null,
    val status: String? = null,
    val totalResults: Int? = null
)