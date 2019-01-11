package com.verse.app.verseapp.repository.models

import com.google.gson.annotations.SerializedName

data class Series(

	@field:SerializedName("collectionURI")
	val collectionURI: String? = null,

	@field:SerializedName("available")
	val available: String? = null,

	@field:SerializedName("returned")
	val returned: String? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)