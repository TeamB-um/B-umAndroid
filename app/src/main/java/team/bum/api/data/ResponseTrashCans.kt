package team.bum.api.data

data class ResponseTrashCans(
    val msg: String,
    val status: Int,
    val success: Boolean,
    val data: List<Trashcan>
)