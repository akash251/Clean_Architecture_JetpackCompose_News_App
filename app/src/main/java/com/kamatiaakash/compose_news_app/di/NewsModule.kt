package com.kamatiaakash.compose_news_app.di

import com.kamatiaakash.compose_news_app.data.remote.NewsApi
import com.kamatiaakash.compose_news_app.data.repository.NewsRepositoryImplementation
import com.kamatiaakash.compose_news_app.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun provideNewsApi():NewsApi{
        return Retrofit.Builder()
            .baseUrl(NewsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi):NewsRepository{
        return NewsRepositoryImplementation(api)
    }
}