package org.example.richandmorty.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.json.Json
import org.example.richandmorty.domain.model.CharacterModel
import org.example.richandmorty.domain.model.CharacterOfTheDayModel

@Entity(tableName = "characteroftheday")
data class CharacterOfTheDayEntity (
    @PrimaryKey val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val selectedDate: String,
    val species: String,
    val gender: String,
    val origin: String,
    val episodes: String
) {
    fun toDomain(): CharacterOfTheDayModel? {
       return CharacterOfTheDayModel(
            characterModel = CharacterModel(id= id,isAlive= isAlive, image, name= name, species = species, gender, origin, Json.decodeFromString(episodes)),
            selectedDay = selectedDate
        )
    }
}