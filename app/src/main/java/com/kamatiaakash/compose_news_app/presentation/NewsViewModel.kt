package com.kamatiaakash.compose_news_app.presentation

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamatiaakash.compose_news_app.domain.repository.NewsRepository
import com.kamatiaakash.compose_news_app.presentation.util.customChromeTab
import com.kamatiaakash.compose_news_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
):ViewModel() {

    private val _state = mutableStateOf(NewsState())
    val state:State<NewsState> = _state


    init {
        getCategoryNewsList(category = "technology")
    }

    fun onSearchQueryChange(query:String){
        _state.value = state.value.copy(
            searchQuery = query
        )
    }

    fun onCategoryIndexChange(index:Int){
        _state.value = state.value.copy(
            selectedCategoryIndex = index
        )
    }

    fun viewFullArticle(url:String,context: Context){
        customChromeTab(url = url, context = context)
    }

    fun getCategoryNewsList(category:String){
        viewModelScope.launch {
            repository.getCategoryNewsList(category = category)
                .collect{res ->
                    when(res){
                        is Resource.Loading ->{
                            _state.value = state.value.copy(
                                isLoading = res.isLoading
                            )
                        }
                        is Resource.Success ->{
                            res.data?.let {
                                println("news = $it")
                                _state.value = state.value.copy(
                                    news = it.article
                                )
                            }

                        }
                        is Resource.Error ->{
                            _state.value = state.value.copy(
                                error = res.message.toString()
                            )
                        }
                    }
                }
        }
    }

     fun getSearchedNews(query:String){
        viewModelScope.launch {
            repository.getSearchedNewsList(query = query)
                .collect{res ->
                    when(res){
                        is Resource.Loading ->{
                           _state.value = state.value.copy(
                               isLoading = res.isLoading
                           )
                        }
                        is Resource.Success ->{
                            res.data?.let {
                                println("news = $it")
                                _state.value = state.value.copy(
                                    news = it.article
                                )
                            }

                        }
                        is Resource.Error ->{
                            _state.value = state.value.copy(
                                error = res.message.toString()
                            )
                        }
                    }
                }
        }
    }
}