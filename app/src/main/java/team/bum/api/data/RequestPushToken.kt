package team.bum.api.data

import com.google.gson.annotations.SerializedName

data class RequestPushToken(
    @SerializedName("pushtoken") val pushToken: String
)
