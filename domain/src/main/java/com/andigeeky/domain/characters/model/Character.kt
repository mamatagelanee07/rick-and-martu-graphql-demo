package com.andigeeky.domain.characters.model

data class Character(
    val id : CharacterId,
    val name : String,
    val status : CharacterStatus,
    val species: String,
    val type : String,
    val gender : CharacterGender,
    val origin : Location,
    val location : Location,
    val image : Url,
    val episodes : List<Url>,
    val url : Url,
    val created : Timestamp
)
