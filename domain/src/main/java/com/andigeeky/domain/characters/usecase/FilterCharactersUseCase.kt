package com.andigeeky.domain.characters.usecase

import com.andigeeky.domain.characters.model.Character
import com.andigeeky.domain.characters.model.CharacterQuery
import com.andigeeky.domain.characters.repository.CharactersRepository

class FilterCharactersUseCase(private val repository: CharactersRepository) {
    suspend operator fun invoke(ids: HashMap<CharacterQuery, String>) : List<Character>{
        return repository.filter(ids)
    }
}