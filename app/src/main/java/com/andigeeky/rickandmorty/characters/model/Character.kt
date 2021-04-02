package com.andigeeky.rickandmorty.characters.model

import com.andigeeky.rickandmorty.graphql.GetCharactersQuery

data class Character(
    val id: ID?,
    val name: String?,
    val url: String?,
    val gender: Gender,
    val episodes: List<Episode>
)

enum class Gender(val gender: String) {
    FEMALE("Female"),
    MALE("Male"),
    GENDERLESS("Genderless"),
    UNKNOWN("unknown");

    companion object {
        fun get(gender: String?): Gender {
            return values().find { it.gender == gender } ?: UNKNOWN
        }
    }
}

data class Episode(
    val id: ID
)

inline class ID(val id: String?)

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