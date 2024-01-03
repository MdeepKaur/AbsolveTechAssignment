package com.example.absolvetechassignment.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.absolvetechassignment.models.Product
import com.example.absolvetechassignment.retrofit.ApiInterface
import com.example.absolvetechassignment.utils.GenericAdapter
import com.example.absolvetechassignment.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskOneViewModel @Inject constructor(private val apiInterface: ApiInterface) : ViewModel() {

    lateinit var productAdapter:GenericAdapter<Product>

    private var _allProductsLiveData = MutableLiveData<NetworkResult<List<Product>>>()
    val allProduct: LiveData<NetworkResult<List<Product>>>
        get() = _allProductsLiveData

    fun getAllProducts() {
        viewModelScope.launch {
            _allProductsLiveData.postValue(NetworkResult.Loading())
            val response = apiInterface.getAllProducts()
            if (response.isSuccessful && response.body() != null) {
                _allProductsLiveData.postValue(NetworkResult.Success<List<Product>>(response.body()))
            } else if (response.errorBody() != null) {
                _allProductsLiveData.postValue(NetworkResult.Error("Something Went wrong"))
            } else {
                _allProductsLiveData.postValue(NetworkResult.Error("Something Went wrong"))
            }
        }
    }
}