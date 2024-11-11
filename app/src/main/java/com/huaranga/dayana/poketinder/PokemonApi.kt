package com.huaranga.dayana.poketinder
import retrofit2.http.GET
import retrofit2.Response
interface PokemonApi {
    @GET("/api/v2/pokemon")
    suspend fun getPokemons(): Response<PokemonListResponse>
}