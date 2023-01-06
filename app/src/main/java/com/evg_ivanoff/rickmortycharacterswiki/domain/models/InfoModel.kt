package com.evg_ivanoff.rickmortycharacterswiki.domain.models

data class InfoModel (
    val count: Int = 0,
    val pages: Int = 0,
    val next: String? = null,
    val prev: String? = null
)