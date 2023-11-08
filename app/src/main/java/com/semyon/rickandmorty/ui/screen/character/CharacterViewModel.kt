package com.semyon.rickandmorty.ui.screen.character

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import com.semyon.rickandmorty.data.model.CharacterModel
import com.semyon.rickandmorty.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {

    val characters: Flow<PagingData<CharacterModel>> = repository.getAllCharacter()
        .map { pagingData -> pagingData.map { characterModel: CharacterModel -> characterModel } }

}