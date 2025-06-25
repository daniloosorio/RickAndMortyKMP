package org.example.richandmorty.data

import androidx.paging.PagingConfig
import app.cash.paging.Pager
import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.example.richandmorty.data.remote.ApiService
import org.example.richandmorty.data.remote.paging.CharactersPagingSource
import org.example.richandmorty.data.remote.response.CharacterResponse
import org.example.richandmorty.domain.Repository
import org.example.richandmorty.domain.model.CharacterModel

class RepositoryImpl(private val api: ApiService, private val characterPaginSource: CharactersPagingSource): Repository {

    companion object {
        const val MAX_ITEMS = 20
        const val PREFETCH_ITEMS = 5
    }
    override suspend fun getSingleCharacter(id: String): CharacterModel {
       return api.getSingleCharacter(id).toDomain()
    }

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return  Pager(config = PagingConfig(pageSize = MAX_ITEMS,prefetchDistance =PREFETCH_ITEMS),
            pagingSourceFactory = {characterPaginSource}).flow
    }
}
