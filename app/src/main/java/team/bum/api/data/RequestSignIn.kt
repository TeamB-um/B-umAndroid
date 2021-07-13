package team.bum.api.data

import com.google.gson.annotations.SerializedName

data class RequestSignIn(
    @SerializedName("device_id") val uuid: String
)