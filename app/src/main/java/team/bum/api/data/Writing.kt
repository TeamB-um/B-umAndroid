package team.bum.api.data

data class Writing(
    val _id: String,
    val category: Category,
    val created_date: String,
    val text: String,
    val title: String
)