package com.semyon.rickandmorty.data.network

import com.semyon.rickandmorty.data.model.CharacterModel
import com.semyon.rickandmorty.data.network.response.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("/api/character")
    suspend fun getCharacter(
        @Query("page") page: Int,
    ): ListResponse<CharacterModel>

}