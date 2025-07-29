package org.example.richandmorty.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.example.richandmorty.data.remote.ApiService
import org.example.richandmorty.domain.model.EpisodeModel

class EpisodePagingSource(private val api: ApiService) : PagingSource<Int, EpisodeModel>() {
    override fun getRefreshKey(state: PagingState<Int, EpisodeModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeModel> {
        return try {
            val page = params.key ?: 1
            val response = api.getAllEpisodes(page)
            val episodes = response.result

            val prev = if (page > 1) page - 1 else null
            val next = if (response.info.next != null) page + 1 else null

            LoadResult.Page(
                data = episodes.map { it.toDomain() },
                prevKey = prev,
                nextKey = next
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}