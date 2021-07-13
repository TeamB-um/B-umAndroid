package team.bum.api.data

import com.google.gson.annotations.SerializedName

data class ResponseWriting(
    val status: Int,
    val success: Boolean,
    @SerializedName("writingresult") val writingResult: WritingResult
) {
    data class WritingResult(
        @SerializedName("_id") val id: String,
        val category: Category,
        @SerializedName("created_date") val date: String,
        val text: String,
        val title: String
    )
}