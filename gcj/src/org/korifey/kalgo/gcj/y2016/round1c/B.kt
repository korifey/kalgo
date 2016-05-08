package org.korifey.kalgo.gcj.y2016.round1c

import org.korifey.kalgo.gcj.base.GcjBase
import org.korifey.kalgo.util.digitsFromHiToLo
import java.io.PrintStream
import java.util.*

internal class B : GcjBase() {




    override fun case(scanner: Scanner, out: PrintStream) {
        val n = scanner.nextInt()
        val m = scanner.nextLong()

        if (m > (1L shl (n-2))) {
            out.print("IMPOSSIBLE")
            return
        } else {
            out.print("POSSIBLE")
        }

        val m1 = m - 1
        val s = m1.digitsFromHiToLo(2).joinToString("").padStart(n-1,'0')+"1"
        out.println()
        out.print(s)
        for (i in 2.. n) {
            out.println()
            out.print("".padStart(i, '0').padEnd(n,'1'))
        }

    }
}