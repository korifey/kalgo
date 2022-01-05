package org.korifey.kalgo.codeforces.huawei

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val y = scanner.nextInt()

    val values = listOf("rat", "ox", " tiger", "rabbit", " dragon", "snake", "horse", "goat", "monkey", "rooster", "dog", "pig")
    println(values[(y-1900) % 12])
}