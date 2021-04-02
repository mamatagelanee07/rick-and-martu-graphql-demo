package com.andigeeky.rickandmorty.characters.di

import com.andigeeky.rickandmorty.characters.data.CharactersDataSource
import com.andigeeky.rickandmorty.characters.ui.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characters_module = module {
    factory { CharactersDataSource(get()) }
    viewModel { CharactersViewModel(get()) }
}