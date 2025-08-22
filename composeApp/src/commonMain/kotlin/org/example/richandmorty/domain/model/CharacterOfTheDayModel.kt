package org.example.richandmorty.domain.model

import org.example.richandmorty.data.database.entity.CharacterOfTheDayEntity

data class CharacterOfTheDayModel (
    val characterModel: CharacterModel,
    val selectedDay: String
) {
    fun toEntity(): CharacterOfTheDayEntity {
        return CharacterOfTheDayEntity(
            id = characterModel.id,
            isAlive = characterModel.isAlive,
            image = characterModel.image,
            name = characterModel.name,
            selectedDate = selectedDay,
            species = characterModel.species
        )
    }
}