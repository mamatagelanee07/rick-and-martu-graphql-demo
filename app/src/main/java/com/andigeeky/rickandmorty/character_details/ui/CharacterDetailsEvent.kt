package com.andigeeky.rickandmorty.character_details.ui

import androidx.annotation.StringRes
import io.uniflow.core.flow.data.UIEvent

sealed class CharacterDetailsEvent : UIEvent(){
    object ShowLoadingCharacterDetails : CharacterDetailsEvent()
    object HideLoadingCharacterDetails : CharacterDetailsEvent()
    data class ShowPersistentError(@StringRes val message : Int) : CharacterDetailsEvent()
}
