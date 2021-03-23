package com.andigeeky.domain.characters.repository

import com.andigeeky.domain.characters.model.Character
import com.andigeeky.domain.characters.model.CharacterPages
import com.andigeeky.domain.characters.model.CharacterQuery

interface CharactersRepository {
    fun getAll() : CharacterPages
    fun get(id: String) : Character
    fun get(id: List<String>) : List<Character>
    fun filter(id: HashMap<CharacterQuery, String>) : List<Character>
}