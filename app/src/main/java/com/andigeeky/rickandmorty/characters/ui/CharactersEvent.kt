package com.andigeeky.rickandmorty.characters.ui

import androidx.annotation.StringRes
import io.uniflow.core.flow.data.UIEvent

sealed class CharactersEvent : UIEvent(){
    object ShowLoadingCharacters : CharactersEvent()
    object HideLoadingCharacters : CharactersEvent()
    object ShowLoadingNextPageCharacters : CharactersEvent()
    object HideLoadingNextPageCharacters : CharactersEvent()
    data class ShowPersistentError(@StringRes val message : Int) : CharactersEvent()
    data class LoadNextCharactersPage(val page : Int?) : CharactersEvent()
}
