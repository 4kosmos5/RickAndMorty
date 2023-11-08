package com.example.rickandmortysemyon.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortysemyon.data.model.CharacterModel
import com.example.rickandmortysemyon.data.network.CharacterService
import com.example.rickandmortysemyon.data.repository.paged.CharacterPagingSource
import kotlinx.coroutines.flow.Flow

class CharacterRepository(private val api: CharacterService) {

    fun getAllCharacter(): Flow<PagingData<CharacterModel>> {
        return Pager(PagingConfig(20)) {
            CharacterPagingSource(api)
        }.flow
    }

}