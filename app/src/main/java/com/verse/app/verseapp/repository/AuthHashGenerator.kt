package com.verse.app.verseapp.repository

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and
import kotlin.experimental.or

class AuthHashGenerator {
    companion object {
        @Throws(Exception::class)
        fun generateHash(timestamp: String, publicKey: String, privateKey: String): String {
            try {
                val value = timestamp + privateKey + publicKey
                val md5Encoder = MessageDigest.getInstance("MD5")
                val md5Bytes = md5Encoder.digest(value.toByteArray())

                val md5 = StringBuilder()
                for (i in md5Bytes.indices) {
                    md5.append(Integer.toHexString(md5Bytes[i].toInt() and 0xFF or 0x100).substring(1, 3))
                }
                return md5.toString()
            } catch (e: NoSuchAlgorithmException) {
                throw Exception("cannot generate the api key", e)
            }

        }
    }

}