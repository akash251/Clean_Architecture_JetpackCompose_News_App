package com.kamatiaakash.compose_news_app.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kamatiaakash.compose_news_app.domain.model.Article

@Composable
fun NewsListItem(
    article: Article,
    onNewsItemClick:() -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clip(shape = RoundedCornerShape(20.dp))
        .clickable { onNewsItemClick() }
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.LightGray)
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
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
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}