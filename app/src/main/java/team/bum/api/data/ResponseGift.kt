package team.bum.api.data

data class ResponseGift(
    val data: Data,
    val success: Boolean
) {
    data class Data(
        val present: Present
    ) {
        data class Present(
            val _id: String,
            val sentence: String
        )
    }
}