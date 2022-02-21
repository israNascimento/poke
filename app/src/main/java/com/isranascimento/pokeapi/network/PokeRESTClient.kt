package com.isranascimento.pokeapi.network

import retrofit2.Response
import retrofit2.http.GET

interface PokeRESTClient {
    @GET("/pokemons")
    suspend fun findAll(): Response<List<PokemonDTO>>
}