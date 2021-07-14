package team.bum.api.data

import com.google.gson.annotations.SerializedName

data class ResponseStats(
    val data: Data,
    val success: Boolean
) {
    data class Data(
        @SerializedName("allstat") val allStat: List<AllStat>,
        @SerializedName("monthstat") val monthStat: List<MonthStat>
    )
}