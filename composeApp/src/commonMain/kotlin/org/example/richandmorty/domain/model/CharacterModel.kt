package org.example.richandmorty.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterModel (
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String
)