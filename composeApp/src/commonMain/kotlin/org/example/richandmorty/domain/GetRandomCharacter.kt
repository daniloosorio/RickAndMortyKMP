package org.example.richandmorty.domain

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.richandmorty.domain.model.CharacterModel
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class GetRandomCharacter(val repository: Repository) {

    suspend operator fun invoke(): CharacterModel{

        val random: Int = (1..826).random()
        return repository.getSingleCharacter(random.toString())
    }

    @OptIn(ExperimentalTime::class)
    private fun getCurrentOfTheYear():String{
        val instant: Instant = Clock.System.now()
        val localTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localTime.dayOfYear}${localTime.year}"
    }
}