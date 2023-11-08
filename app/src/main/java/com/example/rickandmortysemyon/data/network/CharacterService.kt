package com.example.rickandmortysemyon.data.network

import com.example.rickandmortysemyon.data.model.CharacterModel
import com.example.rickandmortysemyon.data.network.response.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("/api/character")
    suspend fun getCharacter(
        @Query("page") page: Int,
    ): ListResponse<CharacterModel>

}