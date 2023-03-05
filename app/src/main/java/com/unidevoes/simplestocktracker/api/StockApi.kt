package com.unidevoes.simplestocktracker.api

import com.unidevoes.simplestocktracker.models.StockResponse
import com.unidevoes.simplestocktracker.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {
    @GET("query")
    suspend fun searchStock(
        @Query("function")
        function: String = "SYMBOL_SEARCH",
        @Query("keywords")
        keywords: String,
        @Query("apikey")
        apiKey: String = API_KEY
    ): Response<StockResponse>
}