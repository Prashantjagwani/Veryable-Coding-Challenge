package com.veryable.android.listener

import com.veryable.android.data.ApiResponseItem

// click listener interface for the recyclerview adapter
interface ItemClickListener {

    fun onItemClick(apiResponseItem: ApiResponseItem?, position: Int)
}