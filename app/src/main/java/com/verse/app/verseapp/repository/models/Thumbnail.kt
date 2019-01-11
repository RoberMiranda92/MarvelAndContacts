package com.verse.app.verseapp.repository.models

import com.google.gson.annotations.SerializedName

data class Thumbnail(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("extension")
	val extension: String? = null
)

fun Thumbnail.getFullPath():String{
	return "$path.$extension"
}