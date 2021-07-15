package team.bum.util

import java.time.DayOfWeek
import java.time.LocalDateTime

/**
 * YYYY.MM.DD
 */
typealias DateString = String

fun String.padZero(length: Int = 2) = padStart(length, '0')
fun Int.padZero() = toString().padZero()

/**
* 1 -> 월
* 7 -> 일
*/
val DayOfWeek.koFormat: String
    get() = when (value) {
        1 -> "월"
        2 -> "화"
        3 -> "수"
        4 -> "목"
        5 -> "금"
        6 -> "토"
        else -> "일"
    }

/**
 * 2021.07.05
 */
val LocalDateTime.dateString: DateString
    get() = "${year}.${monthValue.padZero()}.${dayOfMonth.padZero()}"

/**
 * 2021-07-15
 */
val LocalDateTime.filterDateString: DateString
    get() = "${year}-${monthValue.padZero()}-${dayOfMonth.padZero()}"

/**
 * 2021.07.14 (수)
 */
val LocalDateTime.dateFormat: DateString
    get() = "${year}.${monthValue.padZero()}.${dayOfMonth.padZero()} (${dayOfWeek.koFormat})"

/**
 * 2021년 07월 14일 (수)
 */
val LocalDateTime.koFormat: DateString
    get() = "${year}년 ${monthValue.padZero()}월 ${dayOfMonth.padZero()}일 (${dayOfWeek.koFormat})"