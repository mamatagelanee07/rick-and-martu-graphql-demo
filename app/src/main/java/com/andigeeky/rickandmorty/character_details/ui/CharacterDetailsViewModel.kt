package com.andigeeky.rickandmorty.character_details.ui

import com.andigeeky.rickandmorty.R
import com.andigeeky.rickandmorty.character_details.data.CharacterDetailsDataSource
import com.andigeeky.rickandmorty.character_details.model.map
import io.uniflow.android.AndroidDataFlow

class CharacterDetailsViewModel(private val dataSource: CharacterDetailsDataSource) : AndroidDataFlow() {
    fun loadCharacterDetails(id: String) = action {
        sendEvent(CharacterDetailsEvent.ShowLoadingCharacterDetails)
        val response = dataSource.getCharacterDetails(id)
        when {
            response.data != null -> setState(
                CharacterDetailsState(
                    details = response.data?.character.map()
                )
            )
            response.hasErrors() -> sendEvent(CharacterDetailsEvent.ShowPersistentError(R.string.error_characters_loading))
        }
        sendEvent(CharacterDetailsEvent.HideLoadingCharacterDetails)
    }
}