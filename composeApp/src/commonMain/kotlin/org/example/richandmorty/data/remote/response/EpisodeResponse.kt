package org.example.richandmorty.data.remote.response

import kotlinx.serialization.Serializable
import org.example.richandmorty.domain.model.EpisodeModel
import org.example.richandmorty.domain.model.SeasonEpisode

@Serializable
data class EpisodeResponse (
    val id:Int,
    val name: String,
    val episode:String,
    val characters: List<String>
) {
    fun toDomain(): EpisodeModel {
        return EpisodeModel(
            id =id,
            name= name,
            episode= episode,
            characters = characters.map{ url -> url.substringAfter("/")},
            season = getSeasonFromEpisodeCode(episode),
            videoURL = getVideoUrlFromSeason()
        )
    }

    private fun getSeasonFromEpisodeCode(episode:String): SeasonEpisode {
        return when{
            episode.startsWith("S01") -> SeasonEpisode.SEASON_1
            episode.startsWith("S02") -> SeasonEpisode.SEASON_2
            episode.startsWith("S03") -> SeasonEpisode.SEASON_3
            episode.startsWith("S04") -> SeasonEpisode.SEASON_4
            episode.startsWith("S05") -> SeasonEpisode.SEASON_5
            episode.startsWith("S06") -> SeasonEpisode.SEASON_6
            episode.startsWith("S07") -> SeasonEpisode.SEASON_7
            else -> SeasonEpisode.UNKNOW

        }
    }
}