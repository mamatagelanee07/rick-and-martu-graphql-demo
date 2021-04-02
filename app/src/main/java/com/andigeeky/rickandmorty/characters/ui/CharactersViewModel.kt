package com.andigeeky.rickandmorty.characters.ui

import com.andigeeky.rickandmorty.R
import com.andigeeky.rickandmorty.characters.data.CharactersDataSource
import com.andigeeky.rickandmorty.characters.model.map
import com.andigeeky.rickandmorty.graphql.type.FilterCharacter
import com.apollographql.apollo.api.Input
import io.uniflow.android.AndroidDataFlow

class CharactersViewModel(private val dataSource: CharactersDataSource) : AndroidDataFlow() {
    fun loadCharacters(page: Int = 1, filter: Input<FilterCharacter> = Input.absent()) = action {
        sendEvent(CharactersEvent.ShowLoadingCharacters)
        val response = dataSource.getCharacters(page, filter)
        when {
            response.data != null -> setState(
                CharactersState(
                    characters = response.data?.characters.map()
                )
            )
            response.hasErrors() -> sendEvent(CharactersEvent.ShowPersistentError(R.string.error_characters_loading))
        }
        sendEvent(CharactersEvent.HideLoadingCharacters)
    }
}