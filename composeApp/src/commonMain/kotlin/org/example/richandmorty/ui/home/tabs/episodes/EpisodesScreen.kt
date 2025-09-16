package org.example.richandmorty.ui.home.tabs.episodes

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import org.example.richandmorty.domain.model.EpisodeModel
import org.example.richandmorty.domain.model.SeasonEpisode
import org.example.richandmorty.isDesktop
import org.example.richandmorty.ui.core.BackgroundPrimaryColor
import org.example.richandmorty.ui.core.BackgroundSecondaryColor
import org.example.richandmorty.ui.core.BackgroundTertiaryColor
import org.example.richandmorty.ui.core.DefaultTextColor
import org.example.richandmorty.ui.core.Placeholder
import org.example.richandmorty.ui.core.components.PagingLoadingState
import org.example.richandmorty.ui.core.components.PagingType
import org.example.richandmorty.ui.core.components.PagingWrapper
import org.example.richandmorty.ui.core.components.VideoPlayer
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import richandmorty.composeapp.generated.resources.Res
import richandmorty.composeapp.generated.resources.images
import richandmorty.composeapp.generated.resources.season1
import richandmorty.composeapp.generated.resources.season2
import richandmorty.composeapp.generated.resources.season3
import richandmorty.composeapp.generated.resources.season4
import richandmorty.composeapp.generated.resources.season5
import richandmorty.composeapp.generated.resources.season6
import richandmorty.composeapp.generated.resources.season7

@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodesScreen(
//    episodesViewmodel: EpisodesViewmodel = koinViewModel<EpisodesViewmodel>()
) {
    val episodesViewmodel = koinViewModel<EpisodesViewmodel>()

    val state by episodesViewmodel.state.collectAsState()
    val episodes: LazyPagingItems<EpisodeModel> = state.characters.collectAsLazyPagingItems()

    Column(Modifier.fillMaxSize().background(BackgroundPrimaryColor)) {
        Spacer(Modifier.height(16.dp))
        PagingWrapper(
            pagingType = PagingType.ROW,
            pagingItems = episodes,
            initialView = {
                PagingLoadingState()
            },
            emptyView = {},
            itemView = { EpisodeItemList(it) { url -> episodesViewmodel.onPlaySelectedUrl(url) } }
        )
        EpisodePlayer(state.playVideo) { episodesViewmodel.onCloseVideo() }

    }
}


@Composable
fun EpisodeItemList(episode: EpisodeModel, onEpisodeSelected: (String) -> Unit) {
    Column(
        modifier = Modifier.width(120.dp).padding(horizontal = 8.dp)
            .clickable { onEpisodeSelected(episode.videoURL) }) {
        Image(
            modifier = Modifier.height(180.dp).fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            painter = painterResource(getSeasonImage(episode.season))
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(episode.episode, color = DefaultTextColor, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun EpisodePlayer(playVideo: String, onCloseVideo: () -> Unit) {
    // AnimatedVisibility (playVideo.isNotBlank()) {
    AnimatedContent(playVideo.isNotBlank()) { condition ->
        if (condition) {
            val height = if (isDesktop()) 600.dp else 250.dp
            ElevatedCard(
                modifier = Modifier.fillMaxWidth().height(height).padding(16.dp).border(
                    3.dp,
                    Color.Green, CardDefaults.elevatedShape
                )
            ) {
                Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
                    Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                        VideoPlayer(modifier = Modifier.fillMaxWidth().height(200.dp), playVideo)
                    }
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = { onCloseVideo() }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }
                    }
                }
            }
        } else {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth().height(250.dp).padding(16.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Placeholder)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(Res.drawable.images), contentDescription = null)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "selecciona un video",
                        color = DefaultTextColor,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }

}


fun getSeasonImage(seasonEpisode: SeasonEpisode): DrawableResource {
    return when (seasonEpisode) {
        SeasonEpisode.SEASON_1 -> Res.drawable.season1
        SeasonEpisode.SEASON_2 -> Res.drawable.season2
        SeasonEpisode.SEASON_3 -> Res.drawable.season3
        SeasonEpisode.SEASON_4 -> Res.drawable.season4
        SeasonEpisode.SEASON_5 -> Res.drawable.season5
        SeasonEpisode.SEASON_6 -> Res.drawable.season6
        SeasonEpisode.SEASON_7 -> Res.drawable.season7
        SeasonEpisode.UNKNOW -> Res.drawable.season1

    }
}