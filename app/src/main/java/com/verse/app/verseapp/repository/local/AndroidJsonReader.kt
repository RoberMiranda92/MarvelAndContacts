package com.verse.app.verseapp.repository.local

import android.app.Application
import java.io.BufferedReader
import java.io.InputStreamReader

class AndroidJsonReader {
    companion object {


        fun readJsonFile(application: Application, jsonFile: String): String {
            val buf = StringBuilder()
            val json = application.assets.open("json/" + jsonFile)
            BufferedReader(InputStreamReader(json, "UTF-8"))
                    .use {
                        val list = it.lineSequence().toList()
                        buf.append(list.joinToString("\n"))
                    }

            return buf.toString()
        }
    }
}