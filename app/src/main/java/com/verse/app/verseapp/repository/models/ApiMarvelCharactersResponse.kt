package com.verse.app.verseapp.repository.models

import com.google.gson.annotations.SerializedName

data class ApiMarvelCharactersResponse(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("attributionHTML")
	val attributionHTML: String? = null,

	@field:SerializedName("attributionText")
	val attributionText: String? = null,

	@field:SerializedName("etag")
	val etag: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)