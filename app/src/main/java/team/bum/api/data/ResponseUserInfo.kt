package team.bum.api.data

import com.google.gson.annotations.SerializedName

data class ResponseUser(
    val data: Data,
    val success: Boolean
) {
    data class Data(
        val user: User
    ) {
        data class User(
            @SerializedName("ispush") val isPush: Boolean,
            @SerializedName("delperiod") val delPeriod: Int
        )
    }
}