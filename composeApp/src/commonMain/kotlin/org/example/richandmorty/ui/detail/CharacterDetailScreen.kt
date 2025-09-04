package org.example.richandmorty.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.example.richandmorty.domain.model.CharacterModel
import org.example.richandmorty.domain.model.EpisodeModel
import org.example.richandmorty.isDesktop
import org.example.richandmorty.ui.core.BackgroundPrimaryColor
import org.example.richandmorty.ui.core.BackgroundSecondaryColor
import org.example.richandmorty.ui.core.BackgroundTertiaryColor
import org.example.richandmorty.ui.core.DefaultTextColor
import org.example.richandmorty.ui.core.Green
import org.example.richandmorty.ui.core.Pink
import org.example.richandmorty.ui.core.components.TextTitle
import org.example.richandmorty.ui.core.ex.aliveBorder
import org.example.richandmorty.ui.core.ex.vertical
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf
import richandmorty.composeapp.generated.resources.Res
import richandmorty.composeapp.generated.resources.ic_back
import richandmorty.composeapp.generated.resources.images

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharacterDetailScreen(characterModel: CharacterModel, onBackPressed: () -> Unit) {
    val characterDetailViewModel =
        koinViewModel<CharacterDetailViewModel>(parameters = { parametersOf(characterModel) })
    val state by characterDetailViewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()


    Column(modifier = Modifier.fillMaxSize().background(BackgroundPrimaryColor).verticalScroll(scrollState)) {
        MainHeader(state.characterModel,onBackPressed)
        Spacer(Modifier.height(16.dp))
        Column (modifier = Modifier.fillMaxSize()
            .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10)).background(
                BackgroundSecondaryColor
            )){
            CharacterInformation(state.characterModel)
            CharacterEpisodesList(state.episodes)
        }
    }
}

@Composable
fun CharacterEpisodesList(episodes: List<EpisodeModel>?) {
    ElevatedCard(modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = BackgroundTertiaryColor)) { //other way
        Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
            if (episodes == null) {
                CircularProgressIndicator(color = Green)
            } else {
                Column {
                    TextTitle("Episode List")
                    Spacer(Modifier.height(6.dp))

                    episodes.forEach { episode ->
                        EpisodeItem(episode)
                        Spacer(Modifier.height(4.dp))
                    }
                }
            }

        }
    }
}

@Composable
fun EpisodeItem(episode: EpisodeModel) {
    Text(episode.name, color = Green, fontWeight = FontWeight.Bold)
    Text(episode.episode, color = DefaultTextColor)
}

@Composable
fun CharacterInformation(characterModel: CharacterModel) {
    ElevatedCard(modifier = Modifier.padding(16.dp).fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors().copy(containerColor = BackgroundTertiaryColor)) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            TextTitle("About the character")
            Spacer(Modifier.height(6.dp))
            InformationDetail("Origin", characterModel.origin)
            Spacer(Modifier.height(2.dp))
            InformationDetail("Genero", characterModel.gender)
        }
    }
}

@Composable
fun InformationDetail(title: String, detail: String) {
    Row {
        Text(title, color = DefaultTextColor, fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(4.dp))
        Text(detail, color =Green)
    }
}

@Composable
fun MainHeader(characterModel: CharacterModel, onBackPressed: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
        Image(
            painter = painterResource(Res.drawable.images),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        if(isDesktop()) {
            Icon(
                painter = painterResource(Res.drawable.ic_back),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(16.dp).size(24.dp).clickable{onBackPressed()}
            )
        }
        CharacterHeader(characterModel)
    }
}

@Composable
fun CharacterHeader(characterModel: CharacterModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                characterModel.name,
                color = Pink,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text("Specie ${characterModel.species}", color = Color.Black, fontSize = 16.sp)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(16.dp))
            Box(contentAlignment = Alignment.TopCenter) {
                Box(
                    modifier = Modifier.size(205.dp).clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = characterModel.image,
                        contentDescription = null,
                        modifier = Modifier.size(190.dp).clip(CircleShape)
                            .aliveBorder(characterModel.isAlive),
                        contentScale = ContentScale.Crop,
                    )
                }

                val aliveCopy = if (characterModel.isAlive) "ALIVE" else "DEAD"
                val aliveColor = if (characterModel.isAlive) Green else Color.Red
                Text(
                    aliveCopy,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clip(RoundedCornerShape(30)).background(aliveColor)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            Spacer(Modifier.weight(1f))

        }
    }
}