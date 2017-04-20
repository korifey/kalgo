package org.korifey.kalgo.codeforces.algebra

import java.util.*

private val zeroIntSequence = sequenceOf(0)
private fun Int.digitsFromLoToHi(radix: Int = 10) : Sequence<Int> {
    if (this == 0) return zeroIntSequence

    return Sequence {
        object:Iterator<Int> {
            var x = this@digitsFromLoToHi
            override fun hasNext() = x > 0
            override fun next() = (x % radix).apply { x /= radix }
        }
    }
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()

    for (i in 1..n) {
        if (i.isHappy && n % i == 0) {
            println("YES")
            return
        }
    }
    println("NO")
}

private val Int.isHappy: Boolean
    get() {
        return digitsFromLoToHi().all { it ==4 || it == 7 }
    }
