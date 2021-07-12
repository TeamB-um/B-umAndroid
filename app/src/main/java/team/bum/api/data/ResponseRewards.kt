package team.bum.api.data

data class ResponseRewards(
    val message: String,
    val data: List<Reward>,
    val status: Int,
    val success: Boolean
)