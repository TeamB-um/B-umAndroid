package team.bum.api.data

data class ResponseCategoryReward(
    val data: List<CategoryReward>,
    val message: String,
    val status: Int,
    val success: Boolean
)