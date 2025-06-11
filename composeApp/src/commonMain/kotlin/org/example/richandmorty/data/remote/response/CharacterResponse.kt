package org.example.richandmorty.data.remote.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import org.example.richandmorty.domain.model.CharacterModel

@Serializable
data class CharacterResponse (
    @SerialName("id") val id:Int,
    val status:String,
    val image: String
) {
    fun toDomain(): CharacterModel {
        return CharacterModel(
            id = id,
            image = image,
            isAlive = status.lowercase() == "alive"
        )
    }
}