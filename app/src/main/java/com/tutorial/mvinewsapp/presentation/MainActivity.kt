package com.tutorial.mvinewsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.tutorial.mvinewsapp.BuildConfig
import com.tutorial.mvinewsapp.presentation.ui.theme.MVINewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVINewsAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                            Text(
                                text = "Top News Articles",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray
                            )
                            })
                    },
                    modifier = Modifier.background(Color.Blue)
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize().padding(it),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val viewModel: NewsViewModel = mavericksViewModel()
                        NewsDisplay(viewModel = viewModel)
                    }
                }

            }
        }
    }
}

@Composable
fun NewsDisplay(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel
) {
    Box(
        modifier = modifier.fillMaxSize()
    ){
        val state by viewModel.collectAsState()
        if(state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        state.newsInfo?.let { data ->
            LazyColumn {
                items(data){ article ->
                    NewsCard(newsArticle = article)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MVINewsAppTheme {
        val viewModel: NewsViewModel = mavericksViewModel()
        NewsDisplay(viewModel = viewModel)
    }
}