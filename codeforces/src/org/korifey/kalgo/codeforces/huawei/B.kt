package org.korifey.kalgo.codeforces.huawei

import java.lang.Math.abs
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val m = scanner.nextInt()

    val a = Array(n) { scanner.next().map { if (it == 'R') 1 else 0 }.toTypedArray() }


    for (i in 1 until n) {
        val x = abs(a[i][0]-a[0][0])
        for (j in 0 until m) {
            if (abs(a[i][j] - a[0][j]) != x) {
                println("NO")
                return
            }
        }
    }


    println("YES")
}