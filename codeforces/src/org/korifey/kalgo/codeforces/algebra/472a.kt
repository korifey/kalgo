package org.korifey.kalgo.codeforces.algebra

import java.util.*

private fun eratosphen(n: Int): BooleanArray {
    val res = BooleanArray(n + 1, { true })

    res[0] = false
    res[1] = false

    var s = 2
    while (s * s <= n) {
        if (!res[s]) {
            s++
            continue
        }
        var i = s * s
        while (i <= n) {
            res[i] = false
            i += s
        }
        s++
    }
    return res
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val erat = eratosphen(n)
    for (i in 2..n/2) {
        if (!erat[i] && !erat[n-i]) {
            println("$i ${n-i}")
            break;
        }
    }
}