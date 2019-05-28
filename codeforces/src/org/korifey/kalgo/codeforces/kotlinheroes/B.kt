//package org.korifey.kalgo.codeforces.kotlinheroes

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val y = scanner.nextInt()
    val b = scanner.nextInt()
    val r = scanner.nextInt()

    val minY = minOf(y, b-1, r-2)
    val res = minY*3 + 3

    println(res)

}