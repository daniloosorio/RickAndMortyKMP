package org.example.richandmorty.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.richandmorty.data.remote.response.CharacterResponse

class ApiService(private val client: HttpClient) {

    suspend fun getSingleCharacter(id:String): CharacterResponse{
        return client.get("/api/character/$id").body()
    }
}