package com.andigeeky.domain.characters.usecase

import com.andigeeky.domain.characters.model.CharacterPages
import com.andigeeky.domain.characters.repository.CharactersRepository

class GetAllCharactersUseCase(private val repository: CharactersRepository) {
    suspend operator fun invoke() : CharacterPages{
        return repository.getAll()
    }
}