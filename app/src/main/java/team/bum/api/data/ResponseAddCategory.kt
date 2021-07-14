package team.bum.api.data

data class ResponseAddCategory(
    val data: Data,
    val success: Boolean
) {
    data class Data(
        val category: Category
    ) {
        data class Category(
            val _id: String,
            val count: Int,
            val created_date: String,
            val img: String,
            val index: Int,
            val name: String
        )
    }
}