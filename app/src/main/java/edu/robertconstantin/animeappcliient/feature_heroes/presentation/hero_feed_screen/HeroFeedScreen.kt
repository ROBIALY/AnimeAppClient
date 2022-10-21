package edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_feed_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.components.HeroItem

@Composable
fun HeroFeedScreen(
    imageLoader: ImageLoader,
    viewModel: HeroFeedScreenViewModel = hiltViewModel()
) {

    val state = viewModel.state

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        LazyColumn {
            items(state.heroes) {
                HeroItem(imageLoader = imageLoader, hero = it)
            }
        }
    }
}