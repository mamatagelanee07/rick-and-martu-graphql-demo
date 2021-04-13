package com.andigeeky.rickandmorty.characters.di

import com.apollographql.apollo.ApolloClient
import org.koin.dsl.module

val apollo_module = module(override = true){
    //TODO Move URL to BuildConfig
    single {
        ApolloClient.builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()
    }
}