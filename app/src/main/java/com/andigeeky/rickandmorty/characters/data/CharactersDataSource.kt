package com.andigeeky.rickandmorty.characters.data

import com.andigeeky.rickandmorty.graphql.GetCharactersQuery
import com.andigeeky.rickandmorty.graphql.type.FilterCharacter
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await

class CharactersDataSource(private val apolloClient: ApolloClient) {
    suspend fun getCharacters(page : Int, filter : Input<FilterCharacter>): Response<GetCharactersQuery.Data> {
        return apolloClient.query(GetCharactersQuery(page, filter)).await()
    }
}