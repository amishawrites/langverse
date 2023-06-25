package com.example.translate.classes

import java.io.Serializable

class WordSpanish(
    var spanishWord    : String = "",
    var englishWord    : String = ""
) : Serializable {

    override fun toString(): String {
        return "Words(spanishword=${spanishWord}, " +
                "englishword=${englishWord}"
    }
}