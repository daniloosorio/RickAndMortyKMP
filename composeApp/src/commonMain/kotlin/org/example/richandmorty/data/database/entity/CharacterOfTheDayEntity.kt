package org.example.richandmorty.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.example.richandmorty.domain.model.CharacterModel
import org.example.richandmorty.domain.model.CharacterOfTheDayModel

@Entity(tableName = "characteroftheday")
data class CharacterOfTheDayEntity (
    @PrimaryKey val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val selectedDate: String,
    val species: String
) {
    fun toDomain(): CharacterOfTheDayModel? {
       return CharacterOfTheDayModel(
            characterModel = CharacterModel(id= id,isAlive= isAlive, image, name= name, species = species),
            selectedDay = selectedDate
        )
    }
}