package com.andigeeky.domain.characters.model

data class CharacterPages(
    val count : Int,
    val pages : Int,
    val next : Url?,
    val previous: Url?,
    val characters : List<Character>
)
