package org.example.richandmorty.domain

class GetRandomCharacter(val repository: Repository) {

    suspend operator fun invoke(){
        val random: Int = (1..826).random()
        repository.getSingleCharacter(random.toString())
    }
}