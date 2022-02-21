package com.isranascimento.pokeapi.network

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.isranascimento.pokeapi.network.PokeApiServiceTest.FakePokeRestClient.ExpectedResponse.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PokeApiServiceTest {
    private lateinit var sut: PokeApiService
    
    @Before
    fun setup() {
    
    }
    
    @Test
    fun `WHEN pokeApi responds with success THEN the network response is success with correct body`() = runBlocking {
        sut = PokeApiService(FakePokeRestClient(SUCCESS))
        val list = sut.findAll()
        
        assertThat(list.size).isEqualTo(1)
        assertThat(list[0]).isEqualTo(PokemonDTO("pokemon"))
    }
    
    @Test
    fun `WHEN pokeApi responds with error THEN the network response is error with statuscode`() = runBlocking {
        sut = PokeApiService(FakePokeRestClient(ERORR))
    
        val response = sut.findAll()
        
        assertThat(response).isInstanceOf(TODO("CLASSE DE RESPOSTA DE ERROR"))
        assertThat(response.code).isEqualTo(500)
        // NetworkCode
        // Instance
    }
    
    class FakePokeRestClient(
        val expectedResponse: ExpectedResponse
    ): PokeRESTClient {
        enum class ExpectedResponse {
            SUCCESS, ERORR
        }
        override suspend fun findAll(): Response<List<PokemonDTO>> {
            if(expectedResponse == SUCCESS) {
                return Response.success(listOf(PokemonDTO(
                    "pokemon"
                )))
            }
            return Response.error(500, "{}".toResponseBody())
        }
    }
}