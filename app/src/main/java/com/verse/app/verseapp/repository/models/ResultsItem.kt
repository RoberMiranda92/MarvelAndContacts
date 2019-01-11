package com.verse.app.verseapp.repository.models

import com.google.gson.annotations.SerializedName

data class ResultsItem(

	@field:SerializedName("urls")
	val urls: List<UrlsItem?>? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("stories")
	val stories: Stories? = null,

	@field:SerializedName("series")
	val series: Series? = null,

	@field:SerializedName("comics")
	val comics: Comics? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("modified")
	val modified: String? = null,

	@field:SerializedName("ammount")
	val id: String? = null,

	@field:SerializedName("resourceURI")
	val resourceURI: String? = null,

	@field:SerializedName("events")
	val events: Events? = null
)