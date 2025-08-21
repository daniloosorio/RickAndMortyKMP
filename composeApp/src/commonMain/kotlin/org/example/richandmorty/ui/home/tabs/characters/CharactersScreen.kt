package org.example.richandmorty.ui.home.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import org.example.richandmorty.ui.core.ex.vertical
import org.jetbrains.compose.resources.painterResource
import richandmorty.composeapp.generated.resources.Res
import richandmorty.composeapp.generated.resources.images

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreens(navigateToDetail:(CharacterModel)->Unit,
                      charactersViewModel: CharactersViewModel = koinViewModel<CharactersViewModel>()
) {
    val uiState by charactersViewModel.state.collectAsState()
    val characters = uiState.characters.collectAsLazyPagingItems()
    CharacterLazyGridList(characters,uiState,navigateToDetail)
}

@Composable
fun CharacterLazyGridList(
    characters: LazyPagingItems<CharacterModel>,
    uiState: CharactersState,
    navigateToDetail: (CharacterModel) -> Unit
){
    LazyVerticalGrid (
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){

        item (span = { GridItemSpan(2)}){
            Column {
                Text("Characters", color= Color.Black, fontSize = 24.sp)
                CharacterOfTheDay(uiState.characterOfTheDay)
            }
        }
        when{
            characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
                //carga inicial
                item (span = {GridItemSpan(2)}){
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        CircularProgressIndicator(Modifier.size(64.dp), color = Color.Red)
                    }
                }
            }

            characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
                item {
                    //Api vacia
                    Text("No hay personajes 😩 ")
                }
            }

            else -> {
                //recorreremos los items
                items(characters.itemCount){ pos ->

                    characters[pos]?.let { charactersModel ->
                        CharacterItemList(charactersModel){ character ->
                            //navigate
                            navigateToDetail(character)
                        }
                    }

                }
                if(characters.loadState.append is LoadState.Loading){
                    item (span = {GridItemSpan(2)}){
                        Box(modifier = Modifier.fillMaxWidth().height(100.dp), contentAlignment = Alignment.Center){
                            CircularProgressIndicator(Modifier.size(64.dp), color = Color.Red)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItemList(charactersModel: CharacterModel,onItemSelected: (CharacterModel) -> Unit) {
    Box(modifier = Modifier.clip(RoundedCornerShape(24))
        .border(2.dp, color = Color.Green, shape = RoundedCornerShape(0,24,0,24)).fillMaxWidth().height(150.dp)
        .clickable { onItemSelected(charactersModel) },
        contentAlignment = Alignment.BottomCenter){
        AsyncImage(
            model = charactersModel.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            placeholder = painterResource(Res.drawable.images)
        )
        Box(modifier = Modifier.fillMaxWidth().height(60.dp).background(
            brush = Brush.verticalGradient(
                listOf(
                    Color.Black.copy(0f),
                    Color.Black.copy(0.6f),
                    Color.Black.copy(1f)
                )
            )
        ), contentAlignment = Alignment.Center){
            Text(charactersModel.name, color = Color.White, fontSize = 18.sp)
        }
    }

}

@Composable
fun CharacterOfTheDay(characterModel: CharacterModel? = null) {
    Card(modifier = Modifier.fillMaxWidth().height(400.dp), shape = RoundedCornerShape(12)) {
        if (characterModel == null) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(color = Color.Green)
            }
        } else {
            Box(contentAlignment = Alignment.BottomStart) {
                Box(Modifier.fillMaxSize().background(Color.Green.copy(alpha = 0.5f)))

                AsyncImage(
                    model = characterModel.image,
                    contentDescription = "Character Of the day",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    placeholder = painterResource( Res.drawable.images)
                )
                Box(
                    Modifier.fillMaxSize().background(
                        Brush.horizontalGradient(
                            0f to Color.Black.copy(alpha = 0.9f),
                            0.4f to Color.White.copy(alpha = 0f)
                        )
                    )
                )
                Text(characterModel.name,
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