package com.tutorial.mvinewsapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tutorial.mvinewsapp.domain.news.NewsArticle

@Composable
fun NewsCard(
    newsArticle: NewsArticle,
    modifier: Modifier = Modifier
){
    Card (
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.padding(16.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = newsArticle.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            AsyncImage(
                model = newsArticle.image,
                contentDescription = null,
                modifier = modifier.fillMaxWidth().height(250.dp)
            )
            Text(
                text = newsArticle.description,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

}