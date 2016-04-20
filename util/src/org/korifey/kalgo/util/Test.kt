package org.korifey.kalgo.util

import java.util.*


val r = Random()
fun main(args: Array<String>) {
    val n = 1000000;

    val x1 = intArrayOf(0, 1, 0, 1)
    val x2 = intArrayOf(0, 1, 1, 0)

    var winRate = 0
    var exp1 = 0
    var exp2 = 0
    for (i in 1..n) {
        exp1 += expectation(x1)
        exp2 += expectation(x2)
        winRate += win(x1, x2)
    }

    println( "winRate = %.0f%%".format(winRate / n.toDouble() * 100) );
    println( "exp1 = %.0f".format(exp1 / n.toDouble()) );
    println( "exp2 = %.0f".format(exp2 / n.toDouble()) );
}

inline fun expectation(x: IntArray) : Int {
    val a = IntArray(x.size, {r.nextInt(2)})

    var step = 0
    while (true) {
        if (Arrays.equals(a, x)) return step
        for (i in 0..a.lastIndex-1) a[i] = a[i+1]
        a[a.lastIndex] = r.nextInt(2)
        step ++
    }

}

inline fun win(x1 : IntArray, x2: IntArray) : Int {
    val a = IntArray(x1.size, {r.nextInt(2)})

    while (true) {
        if (Arrays.equals(a, x1)) return 1
        if (Arrays.equals(a, x2)) return 0
        for (i in 0..a.lastIndex-1) a[i] = a[i+1]
        a[a.lastIndex] = r.nextInt(2)
    }
}
