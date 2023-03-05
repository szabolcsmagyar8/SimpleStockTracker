package com.unidevoes.simplestocktracker.repository

import com.unidevoes.simplestocktracker.api.RetrofitInstance

class StockRepository {

    suspend fun getStocks(query: String) =
        RetrofitInstance.api.searchStock(keywords = query)

}