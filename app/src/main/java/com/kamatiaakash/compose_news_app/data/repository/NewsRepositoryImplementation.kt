package com.kamatiaakash.compose_news_app.data.repository

import com.kamatiaakash.compose_news_app.data.mapper.toNews
import com.kamatiaakash.compose_news_app.data.remote.NewsApi
import com.kamatiaakash.compose_news_app.domain.model.News
import com.kamatiaakash.compose_news_app.domain.repository.NewsRepository
import com.kamatiaakash.compose_news_app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class NewsRepositoryImplementation @Inject constructor(
   private val api: NewsApi
):NewsRepository {
    override suspend fun getSearchedNewsList(query:String): Flow<Resource<News>> {
        return flow {
            emit(Resource.Loading(true))
             try {
                val response = api.getAllSearchedNews(query = query)
                emit(Resource.Success(data = response.toNews()))
                emit(Resource.Loading(isLoading = false))
            } catch (e: HttpException) {
                emit(Resource.Loading(isLoading = false))
                emit(Resource.Error(message = "${e.message}"))
            } catch (e: IOException) {
                 emit(Resource.Loading(isLoading = false))
                emit(Resource.Error(message = "${e.message}"))
            } catch (e:Exception){
                 emit(Resource.Loading(isLoading = false))
                 emit(Resource.Error(message = "${e.message}"))
            }
        }
    }

    override suspend fun getCategoryNewsList(category: String): Flow<Resource<News>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val response = api.getAllNewsByCategory(category = category)
                emit(Resource.Success(data = response.toNews()))
                emit(Resource.Loading(isLoading = false))
            } catch (e: HttpException) {
                emit(Resource.Loading(isLoading = false))
                emit(Resource.Error(message = "${e.message}"))
            } catch (e: IOException) {
                emit(Resource.Loading(isLoading = false))
                emit(Resource.Error(message = "${e.message}"))
            } catch (e:Exception){
                emit(Resource.Loading(isLoading = false))
                emit(Resource.Error(message = "${e.message}"))
            }
        }
    }

}
