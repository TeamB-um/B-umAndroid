package team.bum.api.data

import team.bum.ui.main.archive.data.RewardInfo

data class ResponseCategoryReward(
    val data: List<RewardInfo>,
    val message: String,
    val status: Int,
    val success: Boolean
)