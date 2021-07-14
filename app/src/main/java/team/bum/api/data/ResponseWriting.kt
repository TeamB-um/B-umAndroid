package team.bum.api.data

data class ResponseWriting(
    val data: Data,
    val success: Boolean
) {
    data class Data(
        val writing: List<Writing>
    )
}