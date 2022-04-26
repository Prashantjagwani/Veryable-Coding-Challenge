package com.veryable.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.veryable.android.data.ApiResponseItem
import com.veryable.android.retofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// viewmodel for the list screen
class PayoutsListActivityViewModel : ViewModel() {

    // livedata for the list fetched from the api
    var liveDataList: MutableLiveData<MutableList<ApiResponseItem>> = MutableLiveData()

    // livedata for the item data on click
    var itemData: MutableLiveData<ApiResponseItem> = MutableLiveData()

    // observer for the list live data
    fun getLiveDataObserver(): MutableLiveData<MutableList<ApiResponseItem>> {
        return liveDataList
    }

    // observer for the item live data
    fun getLiveItemObserver(): MutableLiveData<ApiResponseItem> {
        return itemData
    }

    // api call to fetch list data
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

    // setter method for the item data on click
    fun onItemClick(apiResponseItem: ApiResponseItem?, position: Int) {
        itemData.value = apiResponseItem
    }

}