package com.verse.app.verseapp.repository.models

import com.google.gson.annotations.SerializedName

data class ApiMarvelCharacter(

        @field:SerializedName("phone")
        val link: String,

        @field:SerializedName("ammount")
        val id: Int,

        @field:SerializedName("name")
        val title: String
)