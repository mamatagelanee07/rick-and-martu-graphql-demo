package com.andigeeky.rickandmorty.character_details.di

import com.andigeeky.rickandmorty.character_details.data.CharacterDetailsDataSource
import com.andigeeky.rickandmorty.character_details.ui.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val character_details_module = module {
    factory { CharacterDetailsDataSource(get()) }
    viewModel { CharacterDetailsViewModel(get()) }
}