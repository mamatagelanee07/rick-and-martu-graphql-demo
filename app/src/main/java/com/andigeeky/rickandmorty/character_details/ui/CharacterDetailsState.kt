package com.andigeeky.rickandmorty.character_details.ui

import com.andigeeky.rickandmorty.character_details.model.CharacterDetails
import io.uniflow.core.flow.data.UIState

data class CharacterDetailsState(
    val details : CharacterDetails
) : UIState()
