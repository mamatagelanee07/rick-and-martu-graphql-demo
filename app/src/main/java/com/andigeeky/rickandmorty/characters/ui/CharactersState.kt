package com.andigeeky.rickandmorty.characters.ui

import com.andigeeky.rickandmorty.characters.model.Character
import io.uniflow.core.flow.data.UIState

data class CharactersState(
    val characters : List<Character>
) : UIState()
