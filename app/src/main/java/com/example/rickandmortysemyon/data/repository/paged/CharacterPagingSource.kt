package com.example.rickandmortysemyon.data.repository.paged

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortysemyon.data.model.CharacterModel
import com.example.rickandmortysemyon.data.network.CharacterService

class CharacterPagingSource(private val service: CharacterService) :
    PagingSource<Int, CharacterModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {

        return try {
            val nextPage = params.key ?: 1
            val characterList = service.getCharacter(nextPage)

            LoadResult.Page(
                data = characterList.results!!,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (characterList.info?.next != null) extractPageNumberFromUrl(characterList.info?.next) else null
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    private fun extractPageNumberFromUrl(url: String): Int {
        val uri = Uri.parse(url)
        val pageStr = uri.getQueryParameter("page")
        return pageStr?.toIntOrNull() ?: 1
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return 1
    }
}