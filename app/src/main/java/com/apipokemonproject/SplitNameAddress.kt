package com.apipokemonproject

/**
 * Create an object that houses a function that helps you to manipulate api end point
 */
object SplitNameAddress {

    fun splitName(pokemonsNameForDetails:String):String{
        return "https://pokeapi.co/api/v2/pokemon/${pokemonsNameForDetails}"
    }
}