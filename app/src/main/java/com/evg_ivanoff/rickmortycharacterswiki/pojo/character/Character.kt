package com.evg_ivanoff.rickmortycharacterswiki.pojo.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


//@Entity(tableName = "character")
data class Character(
    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int = 0,

    //имя
    @SerializedName("name")
    @Expose
    val name: String? = null,

    //статус
    @SerializedName("status")
    @Expose
    val status: String? = null,

    //раса
    @SerializedName("species")
    @Expose
    val species: String? = null,

    //пол
    @SerializedName("gender")
    @Expose
    val gender: String? = null,

    //последнее известное местоположение
    @SerializedName("location")
    @Expose
    val location: Location? = null,

    //аватарка
    @SerializedName("image")
    @Expose
    val image: String? = null,

    //эпизоды - нужно только количество
    @SerializedName("episode")
    @Expose
    val episode: List<String>? = null
)
