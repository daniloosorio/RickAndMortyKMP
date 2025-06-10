package org.example.richandmorty.domain

import org.example.richandmorty.domain.model.CharacterModel

interface Repository {
    suspend fun getSingleCharacter(id: String):CharacterModel
}