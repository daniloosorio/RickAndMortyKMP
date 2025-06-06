package org.example.richandmorty.data.remote.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class CharacterResponse (
    @SerialName("id") val parameter:String,
    val status:String,
    val image: String
)