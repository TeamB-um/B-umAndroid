package team.bum.util

import team.bum.R

enum class Paper (
    val id: Int,
    val backgroundColor: Int,
    val textColor: Int,
    val hintColor: Int,
    val dividerColor: Int,
    val img: Int,
) {
    PAPER_1(
        1,
        R.color.paper_1,
        R.color.header,
        R.color.paper_3,
        R.color.text_grey,
        R.drawable.btn_setting_black,
    ),
    PAPER_2(
        2,
        R.color.paper_2,
        R.color.header,
        R.color.paper_3,
        R.color.icon_grey,
        R.drawable.btn_setting_black,
    ),
    PAPER_3(
        3,
        R.color.paper_3,
        R.color.white,
        R.color.paper_2,
        R.color.paper_2,
        R.drawable.btn_setting_white,
    ),
    PAPER_4(
        4,
        R.color.paper_4,
        R.color.white,
        R.color.paper_2,
        R.color.paper_2,
        R.drawable.btn_setting_white,
    ),
}
