package com.veryable.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.veryable.android.data.ApiResponseItem
import com.veryable.android.retofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PayoutsListActivityViewModel : ViewModel() {

    var liveDataList: MutableLiveData<MutableList<ApiResponseItem>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<MutableList<ApiResponseItem>> {
        return liveDataList
    }

    fun getApiList() {
        val call = RetroInstance.apiService.getUsers()
        call.enqueue(object : Callback<MutableList<ApiResponseItem>> {
            override fun onResponse(
                call: Call<MutableList<ApiResponseItem>>,
                response: Response<MutableList<ApiResponseItem>>
            ) {
                liveDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<MutableList<ApiResponseItem>>, t: Throwable) {
                liveDataList.postValue(null)
            }

        })
    }

}