package com.kamatiaakash.compose_news_app.presentation

import com.kamatiaakash.compose_news_app.domain.model.Article

data class NewsState(
    val news:List<Article> = emptyList(),
    val isLoading:Boolean = false,
    val error:String = "",
    val searchQuery:String = "",
    val categories:List<String> = listOf(
        "technology",
        "business",
        "science",
        "sports",
        "entertainment",
        "general",
        "health"
    ),
    val selectedCategoryIndex:Int = 0,
)
