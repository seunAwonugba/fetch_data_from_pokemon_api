package com.apipokemonproject.api

data class PokemonDataClass(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Result>
)