package team.bum.api.data

import team.bum.ui.main.archive.data.RewardInfo

data class ResponseCategoryReward(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val reward: RewardInfo
    )
}