package org.example.richandmorty.data

import org.example.richandmorty.data.remote.ApiService
import org.example.richandmorty.data.remote.response.CharacterResponse
import org.example.richandmorty.domain.Repository
import org.example.richandmorty.domain.model.CharacterModel

class RepositoryImpl(val api: ApiService): Repository {
    override suspend fun getSingleCharacter(id: String): CharacterModel {
       return api.getSingleCharacter(id).toDomain()
    }
}
