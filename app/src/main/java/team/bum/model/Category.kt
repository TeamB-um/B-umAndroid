package team.bum.model

import team.bum.R

enum class Category(
    val id: Int,
    val textColor: Int,
    val background: Int,
) {
    CATEGORY_1(
        0,
        R.color.blue_2_main,
        R.drawable.gradient1
    ),
    CATEGORY_2(
        1,
        R.color.green_3,
        R.drawable.gradient5
    ),
    CATEGORY_3(
        2,
        R.color.pink_3,
        R.drawable.gradient8
    ),
    CATEGORY_4(
        3,
        R.color.blue_4,
        R.drawable.gradient3
    ),
    CATEGORY_5(
        4,
        R.color.green_5,
        R.drawable.gradient6
    ),
    CATEGORY_6(
        5,
        R.color.green_2_main,
        R.drawable.gradient4
    ),
    CATEGORY_7(
        6,
        R.color.blue_3,
        R.drawable.gradient2
    ),
    CATEGORY_8(
        7,
        R.color.pink_2_main,
        R.drawable.gradient7
    ),
}