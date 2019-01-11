package com.verse.app.verseapp.repository.models

import com.google.gson.annotations.SerializedName

data class ApiGetCharactersRequest(

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("price")
    val price: Double? = null,

    @field:SerializedName("name")
    val title: String? = null
)