package team.bum.api.data

data class Trashcan(
    val _id: String,
    val category: Category,
    val d_day: Int,
    val text: String,
    val title: String
) {
    data class Category(
        val __v: Int,
        val _id: String,
        val count: Int,
        val created_date: String,
        val img: String,
        val index: Int,
        val name: String,
        val user_id: String
    )
}