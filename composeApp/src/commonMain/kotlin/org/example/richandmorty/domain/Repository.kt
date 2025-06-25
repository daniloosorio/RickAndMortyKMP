package org.example.richandmorty.domain

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.example.richandmorty.domain.model.CharacterModel

interface Repository {
    suspend fun getSingleCharacter(id: String):CharacterModel
    fun getAllCharacters():Flow<PagingData<CharacterModel>>
}