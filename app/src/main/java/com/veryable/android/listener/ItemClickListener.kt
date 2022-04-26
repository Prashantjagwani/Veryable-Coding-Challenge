package com.veryable.android.listener

import com.veryable.android.data.ApiResponseItem

interface ItemClickListener {

    fun onItemClick(apiResponseItem: ApiResponseItem?, position: Int)
}