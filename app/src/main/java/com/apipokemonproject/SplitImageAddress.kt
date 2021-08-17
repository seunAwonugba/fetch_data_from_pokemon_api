package com.apipokemonproject

/**
 * Create an object class that takes care of image URL manipulation
 */
object SplitImageAddress {
    fun splitFunction(pokemonName:String):String{
        return "https://img.pokemondb.net/artwork/large/${pokemonName}.jpg"
    }

}