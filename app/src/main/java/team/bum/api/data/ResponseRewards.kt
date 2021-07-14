package team.bum.api.data

data class ResponseRewards(
    val message: String,
    val data: Date,
    val status: Int,
    val success: Boolean
) {
    data class Date(
        val rewards: List<Reward>
    )
}