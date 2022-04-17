package com.evg_ivanoff.rickmortycharacterswiki.pojo.character

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Location(

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("url")
    @Expose
    val url: String? = null
)
