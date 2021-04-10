package com.andigeeky.rickandmorty.characters.model

import com.andigeeky.rickandmorty.common.model.Gender
import com.andigeeky.rickandmorty.common.model.ID
import com.andigeeky.rickandmorty.graphql.GetCharactersQuery

data class Character(
    val id: ID?,
    val name: String?,
    val url: String?,
    val gender: Gender,
    val episodes: List<Episode>
)

data class Episode(
    val id: ID
)

fun GetCharactersQuery.Characters?.map(): List<Character> {
    return this?.results?.map { character ->
        Character(
            name = character?.name.orEmpty(),
            id = ID(character?.id),
            url = character?.image,
            episodes = character?.episode?.map { episode ->
                Episode(id = ID(episode?.id))
            } ?: emptyList(),
            gender = Gender.get(character?.gender)
        )
    } ?: emptyList()
}