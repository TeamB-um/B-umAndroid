package team.bum.api.data

data class ResponseTrashCans(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: List<Trashcan>
)