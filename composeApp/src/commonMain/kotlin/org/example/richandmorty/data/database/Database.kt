package org.example.richandmorty.data.database

import androidx.room.Database
import androidx.room.ConstructedBy
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import org.example.richandmorty.data.database.dao.UserPreferencesDao
import org.example.richandmorty.data.database.entity.CharacterOfTheDayEntity

const val DATABASE_NAME = "rm_app_database.db"

expect object RickMortyCTor: RoomDatabaseConstructor<RickMortyDatabase>

@Database(entities = [CharacterOfTheDayEntity::class], version = 1)
@ConstructedBy(RickMortyCTor::class)
abstract class RickMortyDatabase: RoomDatabase(){
    abstract fun getPreferencesDao() : UserPreferencesDao
}
