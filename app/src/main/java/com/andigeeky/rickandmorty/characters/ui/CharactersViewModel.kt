package com.andigeeky.rickandmorty.characters.ui

import com.andigeeky.rickandmorty.R
import com.andigeeky.rickandmorty.characters.data.CharactersDataSource
import com.andigeeky.rickandmorty.characters.model.map
import com.andigeeky.rickandmorty.graphql.type.FilterCharacter
import com.apollographql.apollo.api.Input
import io.uniflow.android.AndroidDataFlow
import io.uniflow.core.flow.actionOn

class CharactersViewModel(private val dataSource: CharactersDataSource) : AndroidDataFlow() {
    fun loadCharacters(page: Int = 1, filter: Input<FilterCharacter> = Input.absent()) = action {
        sendEvent(CharactersEvent.ShowLoadingCharacters)
        val response = dataSource.getCharacters(page, filter)
        when {
            response.data != null -> setState(
                CharactersState(
                    nextPage = response.data?.characters?.info?.next ?: 0,
                    totalPages = response.data?.characters?.info?.pages,
                    filter = filter,
                    characters = response.data?.characters
                        .map()
                        .sortedBy { it.id?.id?.toInt() }
                        .toSet()
                )
            )
            response.hasErrors() -> sendEvent(CharactersEvent.ShowPersistentError(R.string.error_characters_loading))
        }
        sendEvent(CharactersEvent.HideLoadingCharacters)
    }

    fun loadNextPageCharacters(page: Int) = actionOn<CharactersState> { previous ->
        sendEvent(CharactersEvent.ShowLoadingNextPageCharacters)
        val response = dataSource.getCharacters(page, previous.filter)
        when {
            response.data != null -> setState(
                previous.copy(
                    nextPage = response.data?.characters?.info?.next ?: 0,
                    totalPages = response.data?.characters?.info?.pages,
                    characters = response.data?.characters
                        .map()
                        .plus(previous.characters)
                        .sortedBy { it.id?.id?.toInt() }
                        .toSet()
                )
            )
            response.hasErrors() -> sendEvent(CharactersEvent.ShowPersistentError(R.string.error_characters_loading))
        }
        sendEvent(CharactersEvent.HideLoadingNextPageCharacters)
    }

    fun sendLoadNextPageEvent() = actionOn<CharactersState> {
        it.totalPages?.let { total ->
            if (it.nextPage <= total) {
                sendEvent(CharactersEvent.LoadNextCharactersPage(it.nextPage))
            }
        }
    }
}