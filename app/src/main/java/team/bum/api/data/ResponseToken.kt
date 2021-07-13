package team.bum.api.data

data class ResponseToken(
    val status: Int,
    val success: Boolean,
    val data: Data
) {
    data class Data(
        val token: String
    )
}