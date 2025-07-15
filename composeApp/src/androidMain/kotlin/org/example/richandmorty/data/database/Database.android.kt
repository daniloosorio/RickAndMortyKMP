package org.example.richandmorty.data.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.AndroidSQLiteDriver
import kotlinx.coroutines.Dispatchers


fun getDatabase(context: Context): RickMortyDatabase{
    val dbFile = context.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<RickMortyDatabase>(context,dbFile.absolutePath)
        .setDriver(AndroidSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}