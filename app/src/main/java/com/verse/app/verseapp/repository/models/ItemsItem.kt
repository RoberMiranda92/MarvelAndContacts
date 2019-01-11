package com.verse.app.verseapp.repository.models

import com.google.gson.annotations.SerializedName

data class ItemsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("resourceURI")
	val resourceURI: String? = null
)