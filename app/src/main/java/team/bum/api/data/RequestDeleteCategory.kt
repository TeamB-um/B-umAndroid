package team.bum.api.data

import com.google.gson.annotations.SerializedName

data class RequestDeleteCategory(
    @SerializedName("category_id") val categoryId: String
)
