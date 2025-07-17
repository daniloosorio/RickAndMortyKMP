package org.example.richandmorty.domain

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.example.richandmorty.data.database.entity.CharacterOfTheDayEntity
import org.example.richandmorty.domain.model.CharacterModel
import org.example.richandmorty.domain.model.CharacterOfTheDayModel

interface Repository {
    suspend fun getSingleCharacter(id: String):CharacterModel
    fun getAllCharacters():Flow<PagingData<CharacterModel>>

    suspend fun getCharacterDB(): CharacterOfTheDayModel?
    suspend fun saveCharacterDb(characterOfTheDayModel:CharacterOfTheDayModel)
}