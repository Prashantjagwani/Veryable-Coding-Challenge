package com.veryable.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.veryable.android.data.ApiResponseItem

class PayoutsDetailActivityViewModel : ViewModel() {

    var liveDataList: MutableLiveData<ApiResponseItem> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<ApiResponseItem> {
        return liveDataList
    }

}