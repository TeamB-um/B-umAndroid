package team.bum.api.data

import com.google.gson.annotations.SerializedName

data class RequestWriting(
    @SerializedName("category_id") val categoryId: String,
    val text: String,
    val title: String
)