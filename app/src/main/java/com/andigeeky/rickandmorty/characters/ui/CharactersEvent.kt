package com.andigeeky.rickandmorty.characters.ui

import androidx.annotation.StringRes
import io.uniflow.core.flow.data.UIEvent

sealed class CharactersEvent : UIEvent(){
    object ShowLoadingCharacters : CharactersEvent()
    object HideLoadingCharacters : CharactersEvent()
    data class ShowPersistentError(@StringRes val message : Int) : CharactersEvent()
}
