package org.example.richandmorty.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.richandmorty.domain.model.CharacterModel

@Composable
fun CharacterDetailScreen(characterModel: CharacterModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = characterModel.name)
    }
}