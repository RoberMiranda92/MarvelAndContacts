package com.verse.app.verseapp.features.sendmoney.model.entity

class Contact private constructor() {


    var phone: String? = null
        private set
    var thumbnailValue: String? = null
        private set
    lateinit var name: String
        private set

    var ammount: Double? = null


    constructor(builder: Builder) : this() {
        this.phone = builder.phone
        this.name = builder.name
        this.thumbnailValue = builder.thumbnailValue

    }

    class Builder {

        var phone: String? = null
            private set

        var name: String = ""
            private set

        var thumbnailValue: String? = null
            private set

        fun setContactPhone(phone: String?) =
            apply { this.phone = phone }

        fun setContactName(name: String) =
            apply { this.name = name }

        fun setContactThumbnail(thumbnailValue: String?) =
            apply { this.thumbnailValue = thumbnailValue }


        fun build(): Contact =
            Contact(this)
    }
}