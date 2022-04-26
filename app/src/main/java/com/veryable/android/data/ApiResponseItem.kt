package com.veryable.android.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

// data class for the response
@Parcelize
data class ApiResponseItem(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("account_type")
    var accountType: String? = null,
    @SerializedName("account_name")
    var accountName: String? = null,
    @SerializedName("desc")
    var desc: String? = null
) : Parcelable