package com.andigeeky.rickandmorty.character_details.model

import com.andigeeky.rickandmorty.common.model.CharacterStatus
import com.andigeeky.rickandmorty.common.model.Gender
import com.andigeeky.rickandmorty.common.model.ID
import com.andigeeky.rickandmorty.graphql.GetCharacterDetailsQuery

data class CharacterDetails(
    val id: ID?,
    val name: String?,
    val url: String?,
    val gender: Gender,
    val status: CharacterStatus,
    val spices: String,
    val type: String,
    val origin: Location?,
    val current: Location?,
    val episodes: List<Episode>
)

data class Episode(
    val id: ID,
    val name: String,
    val airDate : String
)

data class Location(
    val id: ID,
    val name: String
)

fun GetCharacterDetailsQuery.Character?.map(): CharacterDetails {
    return CharacterDetails(
        id = ID(this?.id),
        name = this?.name.orEmpty(),
        url = this?.image,
        status = CharacterStatus.get(this?.status),
        spices = this?.species.orEmpty(),
        type = this?.type.orEmpty(),
        gender = Gender.get(this?.gender),
        origin = this?.origin?.let {
            Location(
                id = ID(it.id),
                name = it.name.orEmpty()
            )
        },
        current = this?.location?.let {
            Location(
                id = ID(it.id),
                name = it.name.orEmpty()
            )
        },
        episodes = this?.episode?.map {
            Episode(
                id = ID(it?.id),
                name = it?.name.orEmpty(),
                airDate = it?.air_date.orEmpty()
            )
        } ?: emptyList()
    )
}