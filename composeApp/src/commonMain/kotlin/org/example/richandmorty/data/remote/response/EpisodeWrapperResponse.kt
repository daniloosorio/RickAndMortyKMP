package org.example.richandmorty.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeWrapperResponse (
    val info: InfoResponse,
    val result:List<EpisodeResponse>
)