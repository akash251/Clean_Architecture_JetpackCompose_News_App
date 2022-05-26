package com.kamatiaakash.compose_news_app.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kamatiaakash.compose_news_app.domain.model.Article
import com.kamatiaakash.compose_news_app.presentation.NewsViewModel
import com.ramcosta.composedestinations.annotation.Destination


@Composable
@Destination
fun NewsDetailScreen(
    article: Article,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    Card(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)
        .clip(shape = RoundedCornerShape(20.dp))
    ) {
        LazyColumn(
            modifier = Modifier
                .background(color = Color.LightGray)
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item{

                    Text(text = article.title, style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.height(5.dp))
                    AsyncImage(
                        model = article.urlToImage,
                        contentDescription = "article image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = article.description,
                        style = MaterialTheme.typography.body1,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                if(article.author.isNotBlank()){
                    Text(
                        text = "Author: ${article.author}",
                        style = MaterialTheme.typography.h6,
                    )}
                    Spacer(modifier = Modifier.height(4.dp))
                    Button(
                        onClick = { viewModel.viewFullArticle(url = article.url, context = context) },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Click to view more details")
                    }
                }
            }
        }
}