package com.apipokemonproject

import com.apipokemonproject.api.PokemonDataClass
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("pokemon?limit=1118&offset=0")
    fun getProperties():
            Call<PokemonDataClass>
}