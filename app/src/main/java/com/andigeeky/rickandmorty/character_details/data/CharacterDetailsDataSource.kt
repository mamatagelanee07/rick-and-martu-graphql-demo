package com.andigeeky.rickandmorty.character_details.data

import com.andigeeky.rickandmorty.graphql.GetCharacterDetailsQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await

class CharacterDetailsDataSource(private val apolloClient: ApolloClient) {
    suspend fun getCharacterDetails(id : String): Response<GetCharacterDetailsQuery.Data> {
        return apolloClient.query(GetCharacterDetailsQuery(id)).await()
    }
}