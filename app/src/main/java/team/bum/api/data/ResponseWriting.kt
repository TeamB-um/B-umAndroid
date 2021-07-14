package team.bum.api.data

data class ResponseWriting(
    val data: Data,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val writing: Writing
    ) {
        data class Writing(
            val _id: String,
            val category: Category,
            val category_id: String,
            val created_date: String,
            val text: String,
            val title: String
        ) {
            data class Category(
                val _id: String,
                val count: Int,
                val created_date: String,
                val img: String,
                val index: Int,
                val name: String,
                val user_id: String
            )
        }
    }
}