package com.andigeeky.domain.characters.usecase

import com.andigeeky.domain.characters.model.Character
import com.andigeeky.domain.characters.model.CharacterId
import com.andigeeky.domain.characters.repository.CharactersRepository

class GetCharactersUseCase(private val repository: CharactersRepository) {
    suspend operator fun invoke(ids: List<CharacterId>) : List<Character>{
        return repository.get(ids)
    }
}