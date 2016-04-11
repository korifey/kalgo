package org.korifey.kalgo.gcj.y2016.qual

import org.korifey.kalgo.gcj.base.GcjBase
import java.io.PrintStream
import java.util.*

internal class B : GcjBase() {
    override fun case(scanner: Scanner, out: PrintStream) {
        val s = scanner.next()
        var prev = s[0]
        var res = 0
        for (i in 1..s.length-1) {
            if (s[i] != prev) {
                res++
                prev = s[i]
            }
        }

        if (prev == '-') res++

        out.print("$res")
    }

}
