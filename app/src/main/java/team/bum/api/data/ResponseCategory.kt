package team.bum.api.data

data class ResponseCategory(
    val data: List<CategoryInfo>,
    val status: Int,
    val success: Boolean
)