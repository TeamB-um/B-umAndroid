package team.bum.api.data

import com.google.gson.annotations.SerializedName

data class RequestUser(
    @SerializedName("delperiod") val delPeriod: Int
)
