package org.example.richandmorty.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.richandmorty.data.remote.response.CharacterResponse
import org.example.richandmorty.data.remote.response.CharactersWrapperResponse
import kotlin.contracts.Returns

class ApiService(private val client: HttpClient) {

    suspend fun getSingleCharacter(id:String): CharacterResponse{
        return client.get("/api/character/$id").body()
    }

    suspend fun getAllCharacters(page:Int): CharactersWrapperResponse {
        return client.get(urlString = "/api/character/"){
            parameter("page",page)
        }.body()

    }
}