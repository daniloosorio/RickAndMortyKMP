package org.example.richandmorty.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import org.example.richandmorty.data.database.entity.CharacterOfTheDayEntity

@Dao
interface UserPreferencesDao {

    @Query("SELECT * FROM characteroftheday")
    suspend fun getCharacterOfTheDayDB(): CharacterOfTheDayEntity?
}