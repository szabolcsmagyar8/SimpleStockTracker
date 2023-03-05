package com.unidevoes.simplestocktracker.models

import com.google.gson.annotations.SerializedName

data class Stock(
    @SerializedName("1. symbol")
    val symbol: String?,
    @SerializedName("2. name")
    val name: String?,
    val type: String?,
    val region: String?,
    val marketOpen: String?,
    val marketClose: String?,
    val timezone: String?,
    val currency: String?,
    @SerializedName("9. matchScore")
    val matchScore: String?
) : java.io.Serializable