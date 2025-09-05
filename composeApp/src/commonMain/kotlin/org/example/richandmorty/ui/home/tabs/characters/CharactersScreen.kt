package org.example.richandmorty.ui.home.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.example.richandmorty.domain.model.CharacterModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import org.example.richandmorty.ui.core.BackgroundPrimaryColor
import org.example.richandmorty.ui.core.DefaultTextColor
import org.example.richandmorty.ui.core.Green
import org.example.richandmorty.ui.core.components.PagingLoadingState
import org.example.richandmorty.ui.core.components.PagingType
import org.example.richandmorty.ui.core.components.PagingWrapper
import org.example.richandmorty.ui.core.ex.vertical
import org.jetbrains.compose.resources.painterResource
import richandmorty.composeapp.generated.resources.Res
import richandmorty.composeapp.generated.resources.images

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreens(
    navigateToDetail: (CharacterModel) -> Unit,
    charactersViewModel: CharactersViewModel = koinViewModel<CharactersViewModel>()
) {
    val uiState by charactersViewModel.state.collectAsState()
    val characters = uiState.characters.collectAsLazyPagingItems()
    CharacterLazyGridList(characters,  uiState,navigateToDetail)
}

@Composable
fun CharacterLazyGridList(
    characters: LazyPagingItems<CharacterModel>,
    uiState : CharactersState,
    navigateToDetail: (CharacterModel) -> Unit
) {
    PagingWrapper(
        pagingType = PagingType.VERTICAL_GRID,
        pagingItems = characters,
        initialView = { PagingLoadingState() },
        itemView = { CharacterItemList(it) { character -> navigateToDetail(character) } },
        header = {
            Column {
                Text(
                    "Characters",
                    color = DefaultTextColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(6.dp))
                CharacterOfTheDay(uiState.characterOfTheDay)
            }
        })


        /*item() {

        }*/
}

@Composable
fun CharacterItemList(charactersModel: CharacterModel, onItemSelected: (CharacterModel) -> Unit) {
    Box(
        modifier = Modifier.clip(RoundedCornerShape(24))
            .border(2.dp, color = Green, shape = RoundedCornerShape(0, 24, 0, 24)).fillMaxWidth()
            .height(150.dp)
            .clickable { onItemSelected(charactersModel) },
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = charactersModel.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            placeholder = painterResource(Res.drawable.images)
        )
        Box(
            modifier = Modifier.fillMaxWidth().height(60.dp).background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color.Black.copy(0f),
                        Color.Black.copy(0.6f),
                        Color.Black.copy(1f)
                    )
                )
            ), contentAlignment = Alignment.Center
        ) {
            Text(charactersModel.name, color = Color.White, fontSize = 18.sp)
        }
    }

}

@Composable
fun CharacterOfTheDay(characterModel: CharacterModel? = null) {
    Card(modifier = Modifier.fillMaxWidth().height(400.dp), shape = RoundedCornerShape(12)) {
        if (characterModel == null) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(color = Green)
            }
        } else {
            Box(contentAlignment = Alignment.BottomStart) {
                Box(Modifier.fillMaxSize().background(Green.copy(alpha = 0.5f)))

                AsyncImage(
                    model = characterModel.image,
                    contentDescription = "Character Of the day",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    placeholder = painterResource(Res.drawable.images)
                )
                Box(
                    Modifier.fillMaxSize().background(
                        Brush.horizontalGradient(
                            0f to Color.Black.copy(alpha = 0.9f),
                            0.4f to Color.White.copy(alpha = 0f)
                        )
                    )
                )
                Text(
                    characterModel.name,
                    fontSize = 40.sp,
                    maxLines = 1,
                    minLines = 1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .fillMaxHeight()
                        .vertical()
                        .rotate(-90f)
                )
            }
        }
    }
}