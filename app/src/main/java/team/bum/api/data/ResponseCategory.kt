package team.bum.api.data

import team.bum.ui.main.collection.data.CategoryInfo

data class ResponseCategory(
    val data: Data,
    val success: Boolean
) {
    data class Data(
        val categories: List<CategoryInfo>
    )
}