package com.andigeeky.domain.characters.usecase

import com.andigeeky.domain.characters.model.Character
import com.andigeeky.domain.characters.model.CharacterId
import com.andigeeky.domain.characters.repository.CharactersRepository

class GetCharacterUseCase(private val repository: CharactersRepository) {
    suspend operator fun invoke(id: CharacterId) : Character{
        return repository.get(id)
    }
}