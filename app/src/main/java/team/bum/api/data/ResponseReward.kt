package team.bum.api.data

import team.bum.ui.main.archive.data.RewardInfo

data class ResponseReward(
    val message: String,
    val data: List<RewardInfo>,
    val status: Int,
    val success: Boolean
)