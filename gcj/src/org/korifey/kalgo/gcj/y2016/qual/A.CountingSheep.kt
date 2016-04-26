package org.korifey.kalgo.gcj.y2016.qual

import org.korifey.kalgo.gcj.base.GcjBase
import org.korifey.kalgo.util.digitsFromLoToHi
import java.io.PrintStream
import java.util.*

internal class A : GcjBase() {

    override fun case(scanner: Scanner, out: PrintStream) {
        val n = scanner.nextInt()
        if (n == 0) {
            out.print("INSOMNIA")
        } else {
            var countdown = 10;
            val flag = BooleanArray(10)

            var cur = 0;
            while (countdown > 0) {
                cur += n
                cur.digitsFromLoToHi().forEach { if (!flag[it]) {
                    flag[it] = true
                    countdown --;
                }}
            }

            out.print("$cur")
        }
    }


    override fun generate(out: PrintStream) {
        val n = 1000000
        out.println(n)
        for (i in 1..n) out.println(i)
    }
}



