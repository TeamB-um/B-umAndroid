package team.bum.ui.main.archive.data

data class WritingInfo(
    val _id: String,
    val category: Category,
    val created_date: String,
    val text: String,
    val title: String,
    val user_id: String
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