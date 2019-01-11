package com.verse.app.verseapp.repository

interface Mapper<M, A> {

    fun fromApi(apiModel: A): M

    fun toApi(model: M): A
}