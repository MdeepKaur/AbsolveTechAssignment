package com.example.absolvetechassignment.retrofit

import com.example.absolvetechassignment.models.Product
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")
    suspend fun getAllProducts(): Response<List<Product>>
}