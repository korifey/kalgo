package org.korifey.kalgo.codeforces.huawei

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val b = BooleanArray(1000001)
    val a = Array(n) {
        scanner.nextInt().also { b[it] = true }
    }


    var max = 0
    for (i in 0 until (n-1)) {
        val p2 = a[i+1]+a[i]
        if (a[i+1]+a[i] % 2 == 0 && b[a[i+1]+a[i] / 2]) continue

        var r = 0
        for (k in 0 until n) {
            val x =p2 - a[k]
            if (x >= 0 && x <=100000 && b[x]) r++
        }
        if (r > max) max = r
    }

    println(n - max)


}