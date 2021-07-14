package team.bum.api.data

import com.google.gson.annotations.SerializedName

data class ResponseTrashCans(
    val status: Int,
    val success: Boolean,
    val data: Data
) {
    data class Data(
        @SerializedName("trashresult") val trashResult: List<Trashcan>
    )
}