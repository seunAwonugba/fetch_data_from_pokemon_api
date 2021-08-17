package com.apipokemonproject

import com.apipokemonproject.abilitiesApi.PokemonAbilities
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface to hold end point for pokemon abilities in the second activity
 */
interface AbilitiesInterface {
    @GET("pokemon/{id}")
    fun getData(@Path("id") id:String):
            Call<PokemonAbilities>
}

