package com.veryable.android.data


import com.google.gson.annotations.SerializedName

data class ApiResponseItem(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("account_type")
    var accountType: String? = null,
    @SerializedName("account_name")
    var accountName: String? = null,
    @SerializedName("desc")
    var desc: String? = null
)