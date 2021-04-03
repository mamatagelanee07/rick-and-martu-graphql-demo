package com.andigeeky.rickandmorty.characters.ui

import com.andigeeky.rickandmorty.characters.model.Character
import com.andigeeky.rickandmorty.graphql.type.FilterCharacter
import com.apollographql.apollo.api.Input
import io.uniflow.core.flow.data.UIState

data class CharactersState(
    val nextPage : Int?,
    val totalPages : Int?,
    val filter: Input<FilterCharacter>,
    val characters : Set<Character>
) : UIState()
