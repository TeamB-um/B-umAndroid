package team.bum.api.data

import com.google.gson.annotations.SerializedName

data class RequestWriting(
    val category_id: String,
    val title: String,
    val text: String,
    @SerializedName("iswriting")
    val drop_to: Boolean
)