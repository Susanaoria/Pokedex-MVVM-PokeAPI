package com.example.pokedex.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TypeListResponse(
    @SerializedName("results") @Expose val results: List<TypeName>
)

data class TypeName(
    @SerializedName("name") @Expose val name: String
)
