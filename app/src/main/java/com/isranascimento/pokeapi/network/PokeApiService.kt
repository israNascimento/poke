package com.isranascimento.pokeapi.network

class PokeApiService(
    private val restClient: PokeRESTClient
) {
    suspend fun findAll(): List<PokemonDTO> {
        try {
            val response = restClient.findAll()
            if(response.isSuccessful) {
                return response.body()!!
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }
}