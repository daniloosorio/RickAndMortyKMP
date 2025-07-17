package org.example.richandmorty.domain

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.richandmorty.data.database.entity.CharacterOfTheDayEntity
import org.example.richandmorty.domain.model.CharacterModel
import org.example.richandmorty.domain.model.CharacterOfTheDayModel
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class GetRandomCharacter(val repository: Repository) {

    suspend operator fun invoke(): CharacterModel {
        val characterOfTheDay: CharacterOfTheDayModel? = repository.getCharacterDB()
        val selectedDay = getCurrentOfTheYear()
        return if (characterOfTheDay != null && characterOfTheDay.selectedDay == selectedDay) {
            characterOfTheDay.characterModel
        } else {
            val result = generateRandomCharacter()
            repository.saveCharacterDb(
                CharacterOfTheDayModel(
                    characterModel = result,
                    selectedDay = selectedDay
                )
            )
            result
        }

    }

    private suspend fun generateRandomCharacter(): CharacterModel {
        val random: Int = (1..826).random()
        return repository.getSingleCharacter(random.toString())
    }

    @OptIn(ExperimentalTime::class)
    private fun getCurrentOfTheYear(): String {
        val instant: Instant = Clock.System.now()
        val localTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localTime.dayOfYear}${localTime.year}"
    }
}