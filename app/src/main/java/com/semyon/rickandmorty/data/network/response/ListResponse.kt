package com.semyon.rickandmorty.data.network.response

import com.google.gson.annotations.SerializedName

open class ListResponse<Item> {
    @SerializedName("info")
    val info: Info? = null

    @SerializedName("results")
    val results: List<Item>? = null
}

data class Info(
    @SerializedName("count") val count: Int? = null,
    @SerializedName("pages") val pages: Int? = null,
    @SerializedName("next") val next: String? = null,
    @SerializedName("prev") val prev: String? = null,
)