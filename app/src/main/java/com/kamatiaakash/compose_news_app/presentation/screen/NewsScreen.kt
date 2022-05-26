package com.kamatiaakash.compose_news_app.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kamatiaakash.compose_news_app.presentation.NewsViewModel
import com.kamatiaakash.compose_news_app.presentation.component.NewsListItem
import com.kamatiaakash.compose_news_app.presentation.screen.destinations.NewsDetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalComposeUiApi::class)
@Destination(start = true)
@Composable
fun NewsScreen(
    navigator: DestinationsNavigator,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val state = viewModel.state

    val categories = state.value.categories

    val keyboardController = LocalSoftwareKeyboardController.current


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 10.dp, vertical = 5.dp)) {

       Row(Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.SpaceAround,
           verticalAlignment = Alignment.CenterVertically
       ){
           OutlinedTextField(
               shape = RoundedCornerShape(20.dp),
               singleLine = true,
               value = state.value.searchQuery,
               onValueChange = { viewModel.onSearchQueryChange(it)},
               modifier = Modifier.padding(top = 10.dp, start = 2.dp, bottom = 10.dp),
               maxLines = 1,

           )

           IconButton(onClick = {
               viewModel.getSearchedNews(state.value.searchQuery)
               keyboardController?.hide()
           }
           ) {
               Icon(
                   imageVector = Icons.Default.Search,
                   contentDescription = "search",
                   modifier = Modifier.size(40.dp),
                   tint = MaterialTheme.colors.primary
               )
           }
       }


        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(categories.size) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(start = 3.dp, top = 15.dp, bottom = 15.dp)
                        .clickable {
                            viewModel.onCategoryIndexChange(it)
                            viewModel.getCategoryNewsList(category = categories[it])
                        }
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            if (state.value.selectedCategoryIndex == it) Color.Blue
                            else Color.Gray
                        )
                        .padding(15.dp)
                ) {
                    Text(text = categories[it], color = Color.White)
                }
            }
        }


        if (state.value.news.isNotEmpty()) {
            LazyColumn {
                items(state.value.news) { news ->
                    NewsListItem(article = news, onNewsItemClick = {
                        navigator.navigate(
                            NewsDetailScreenDestination(news)
                        )
                    })
                }
            }
        }
        if (state.value.error.isNotBlank()){
            Box(modifier = Modifier.fillMaxSize()){
                Text(
                    text= state.value.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
    if(state.value.isLoading){
        Box(modifier = Modifier.fillMaxSize()){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}