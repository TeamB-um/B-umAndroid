package team.bum.api.data

import com.google.gson.annotations.SerializedName

data class ResponseUserInfo(
    val data: Data,
    val success: Boolean
) {
    data class Data(
        @SerializedName("delperiod") val deletePeriod: Int,
        @SerializedName("ispush") val isPush: Boolean,
        val seq: Int
    )
}