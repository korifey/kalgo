package org.korifey.kalgo.codeforces.algebra

import java.util.*


fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    if (n > 0) {
        print(n)
        return
    }
    val res = Math.max(n / 10, n / 100 * 10 + n % 10)
    print(res)
}    
    