package com.semyon.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.semyon.rickandmorty.data.model.CharacterModel
import com.semyon.rickandmorty.data.network.CharacterService
import com.semyon.rickandmorty.data.repository.paged.CharacterPagingSource
import kotlinx.coroutines.flow.Flow

class CharacterRepository(private val api: CharacterService) {

    fun getAllCharacter(): Flow<PagingData<CharacterModel>> {
        return Pager(PagingConfig(20)) {
            CharacterPagingSource(api)
        }.flow
    }

}