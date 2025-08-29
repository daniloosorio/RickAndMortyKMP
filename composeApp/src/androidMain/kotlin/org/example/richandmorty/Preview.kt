package org.example.richandmorty

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.richandmorty.domain.model.CharacterModel
import org.example.richandmorty.ui.home.tabs.characters.CharacterOfTheDay

@Preview
@Composable
fun Preview (){
    CharacterOfTheDay(CharacterModel(
        id = 3,
        isAlive = true,
        image = "",
        name = "preview",
        species = "human",
        gender = "male",
        origin = "earth",
        episodes = listOf()
    ))
}