package team.bum.api.data

data class ResponseToken(
    val status: Int,
    val success: Boolean,
    val token: String
)