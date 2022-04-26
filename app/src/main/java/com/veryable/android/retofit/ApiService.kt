package com.veryable.android.retofit

import com.veryable.android.data.ApiResponseItem
import retrofit2.Call
import retrofit2.http.GET

// service class fro retrofit with all the endpoints defined
interface ApiService {

    @GET("veryable.json")
    fun getUsers(): Call<MutableList<ApiResponseItem>>
}