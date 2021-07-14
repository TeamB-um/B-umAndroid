package team.bum.api.data

import team.bum.ui.main.archive.data.RewardInfo

data class ResponseRewards(
    val data: Date,
    val status: Int,
    val success: Boolean
) {
    data class Date(
        val rewards: List<RewardInfo>
    )
}