package org.example.richandmorty.ui.home.tabs.episodes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import org.example.richandmorty.domain.model.EpisodeModel
import org.example.richandmorty.domain.model.SeasonEpisode
import org.example.richandmorty.ui.core.components.PagingLoadingState
import org.example.richandmorty.ui.core.components.PagingType
import org.example.richandmorty.ui.core.components.PagingWrapper
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodesScreen(
    episodesViewmodel: EpisodesViewmodel = koinViewModel<EpisodesViewmodel>()
) {
   val episodesViewmodel = koinViewModel<EpisodesViewmodel>()

    val state by episodesViewmodel.state.collectAsState()
    val episodes: LazyPagingItems<EpisodeModel> = state.characters.collectAsLazyPagingItems()

    Box(Modifier.fillMaxSize()){
        PagingWrapper(
            pagingType = PagingType.ROW,
            pagingItems = episodes,
            initialView = {
                PagingLoadingState()
            },
            emptyView = {},
            itemView = { EpisodeItemList(it)}
        )
    }
}

@Composable
fun EpisodeItemList(episodeModel: EpisodeModel){
    Column(modifier = Modifier.width(120.dp).padding(horizontal = 8.dp).clickable{}){
        Image(
            modifier = Modifier.height(200.dp).fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            painter = painterResource()
        )
    }
}

fun getSeasonImage(seasonEpisode: SeasonEpisode): DrawableResource {
    return when(seasonEpisode){
        SeasonEpisode.SEASON_1 -> TODO()
        SeasonEpisode.SEASON_2 -> TODO()
        SeasonEpisode.SEASON_3 -> TODO()
        SeasonEpisode.SEASON_4 -> TODO()
        SeasonEpisode.SEASON_5 -> TODO()
        SeasonEpisode.SEASON_6 -> TODO()
        SeasonEpisode.SEASON_7 -> TODO()
        SeasonEpisode.UNKNOW -> TODO()
    }
}