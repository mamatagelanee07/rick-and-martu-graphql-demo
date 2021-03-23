package com.andigeeky.domain.characters.repository

import com.andigeeky.domain.characters.model.Character
import com.andigeeky.domain.characters.model.CharacterId
import com.andigeeky.domain.characters.model.CharacterPages
import com.andigeeky.domain.characters.model.CharacterQuery

interface CharactersRepository {
    suspend fun getAll() : CharacterPages
    suspend fun get(id: CharacterId) : Character
    suspend fun get(id: List<CharacterId>) : List<Character>
    suspend fun filter(id: HashMap<CharacterQuery, String>) : List<Character>
}