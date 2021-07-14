package team.bum.api.data

data class ResponseCategory(
    val data: Data,
    val success: Boolean
) {
    data class Data(
        val category: List<Category>
    )
}