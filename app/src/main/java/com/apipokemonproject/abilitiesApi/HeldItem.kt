package com.apipokemonproject.abilitiesApi

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)