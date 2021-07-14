package team.bum.api.data

import team.bum.ui.main.archive.data.WritingInfo

data class ResponseMyWriting(
    val data: Data,
    val success: Boolean
) {
    data class Data(
        val writings: List<WritingInfo>
    )
}