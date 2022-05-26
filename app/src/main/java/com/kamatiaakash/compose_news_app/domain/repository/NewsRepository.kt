package com.kamatiaakash.compose_news_app.domain.repository

import com.kamatiaakash.compose_news_app.domain.model.News
import com.kamatiaakash.compose_news_app.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getSearchedNewsList(query:String):Flow<Resource<News>>

    suspend fun getCategoryNewsList(category:String):Flow<Resource<News>>
}