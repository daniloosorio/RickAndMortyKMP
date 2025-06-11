package org.example.richandmorty.domain

import org.example.richandmorty.domain.model.CharacterModel

class GetRandomCharacter(val repository: Repository) {

    suspend operator fun invoke(): CharacterModel{
        val random: Int = (1..826).random()
        return repository.getSingleCharacter(random.toString())
    }
}