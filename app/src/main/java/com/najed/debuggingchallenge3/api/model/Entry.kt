package com.najed.debuggingchallenge3.api.model

import com.google.gson.annotations.SerializedName


class Entry: ArrayList<EntryItem>()

data class EntryItem (
    @SerializedName("meanings")
    val meanings: List<Meaning>,
    @SerializedName("word")
    val word: String
)

data class Meaning (
    @SerializedName("definitions")
    val definitions: List<Definition>
)

data class Definition (
    @SerializedName("definition")
    val definition: String
)