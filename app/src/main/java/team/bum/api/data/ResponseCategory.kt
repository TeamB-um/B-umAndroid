package team.bum.api.data

data class ResponseCategory(
    val data: Data,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val categories: List<CategoryInfo>
    )
}