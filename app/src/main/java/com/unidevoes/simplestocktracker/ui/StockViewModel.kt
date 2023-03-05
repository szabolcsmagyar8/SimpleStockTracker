package com.unidevoes.simplestocktracker.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unidevoes.simplestocktracker.models.StockResponse
import com.unidevoes.simplestocktracker.repository.StockRepository
import com.unidevoes.simplestocktracker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response

@HiltViewModel
class StockViewModel : ViewModel() {


    var stockRepository: StockRepository = StockRepository()

    private val _stocks = MutableLiveData<Resource<StockResponse>>()
    val stocks: LiveData<Resource<StockResponse>> = _stocks

    init {
        getStocks("ibm")
    }

    private fun getStocks(query: String) = viewModelScope.launch {
        _stocks.postValue(Resource.Loading())
        val response = stockRepository.getStocks(query)
        _stocks.postValue(handleStocksResponse(response))
    }

    private fun handleStocksResponse(response: Response<StockResponse>): Resource<StockResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}