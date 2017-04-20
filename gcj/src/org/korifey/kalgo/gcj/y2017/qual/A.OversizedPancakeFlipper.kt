package org.korifey.kalgo.gcj.y2017.qual

import org.korifey.kalgo.gcj.base.GcjBase
import java.io.PrintStream
import java.util.*

internal class A : GcjBase() {

    override fun case(scanner: Scanner, out: PrintStream) {
        val cakes = scanner.next("([+-])*").toCharArray()
        val k = scanner.nextInt()

        var res = 0
        for (i in 0..cakes.size - k) {
            if (cakes[i] == '+') continue
            res++
            for (j in i..i+k-1) {
                if (cakes[j] == '+') cakes[j] = '-'
                else cakes[j] = '+'
            }
        }
        if (cakes.any { it == '-' }) out.print("IMPOSSIBLE")
        else out.print(res)
    }
}