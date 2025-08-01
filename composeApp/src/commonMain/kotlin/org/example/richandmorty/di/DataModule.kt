package org.example.richandmorty.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.richandmorty.data.RepositoryImpl
import org.example.richandmorty.data.remote.ApiService
import org.example.richandmorty.data.remote.paging.CharactersPagingSource
import org.example.richandmorty.data.remote.paging.EpisodePagingSource
import org.example.richandmorty.domain.Repository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation){
                json(json = Json{ignoreUnknownKeys = true}, contentType = ContentType.Any)
            }
            install(DefaultRequest){
                url{
                    protocol = URLProtocol.HTTPS
                    host = "rickandmortyapi.com"
                    //parameters.append("key","apikey")
                }
            }
        }
    }
    factoryOf(::ApiService)
    factory < Repository>{ RepositoryImpl(get(),get(),get(),get()) }
    factoryOf(::CharactersPagingSource)
    factoryOf(::EpisodePagingSource)
}