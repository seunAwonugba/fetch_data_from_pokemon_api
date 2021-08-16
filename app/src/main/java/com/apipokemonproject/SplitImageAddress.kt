package com.apipokemonproject

object SplitImageAddress {
    fun splitFunction(pokemonNumber:Int):String{
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonNumber}.png"
    }

}