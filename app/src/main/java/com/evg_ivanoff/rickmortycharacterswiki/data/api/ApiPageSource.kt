package com.evg_ivanoff.rickmortycharacterswiki.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.evg_ivanoff.rickmortycharacterswiki.data.repository.CharacterRepoImpl
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.CharacterModel
import com.evg_ivanoff.rickmortycharacterswiki.domain.usecases.GetCharactersListByPageUsecase

class ApiPageSource : PagingSource<Int, CharacterModel>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    private val charRepo by lazy(LazyThreadSafetyMode.NONE) { CharacterRepoImpl(ApiFactory) }
    private val getCharactersListByPageUsecase by lazy(LazyThreadSafetyMode.NONE) {
        GetCharactersListByPageUsecase(charRepo)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize

        val characters = getCharactersListByPageUsecase.execute(page).characterOnes
        return if (characters != null) {
            val nextKey = if (characters.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(characters, prevKey, nextKey)
        } else {
            val nextKey = null
            val prevKey = null
            LoadResult.Page(listOf<CharacterModel>(), prevKey, nextKey)
        }
    }
}