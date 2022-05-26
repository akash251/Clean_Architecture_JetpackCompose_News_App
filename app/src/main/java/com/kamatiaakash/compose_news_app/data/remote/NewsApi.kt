package com.kamatiaakash.compose_news_app.data.remote

import com.kamatiaakash.compose_news_app.data.remote.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getAllSearchedNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey : String = "1ec38d7025df4d929ce55bea1061dc04"
    ):NewsDto


    @GET("top-headlines")
    suspend fun getAllNewsByCategory(
        @Query("country") country:String = "us",
        @Query("category") category:String,
        @Query("apiKey") apiKey:String = "1ec38d7025df4d929ce55bea1061dc04"
    ):NewsDto


    companion object{
        const val BASE_URL = "https://newsapi.org/v2/"
    }
}