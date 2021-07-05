package team.bum.util

import java.time.LocalDateTime

/**
 * YYYY.MM.DD
 */
typealias DateString = String

fun String.padZero(length: Int = 2) = padStart(length, '0')
fun Int.padZero() = toString().padZero()

/**
 * 2021.07.05
 */
val LocalDateTime.dateString: DateString
    get() = "${year}.${monthValue.padZero()}.${dayOfMonth.padZero()}"