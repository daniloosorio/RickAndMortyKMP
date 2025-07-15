package org.example.richandmorty.di

import org.example.richandmorty.data.database.RickMortyDatabase
import org.example.richandmorty.data.database.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module{
        single<RickMortyDatabase> { getDatabase(get()) }
    }
}