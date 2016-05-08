package org.korifey.kalgo.gcj.y2016.round1c

import org.korifey.kalgo.gcj.base.GcjBase
import java.io.PrintStream
import java.util.*

internal class A : GcjBase() {




    override fun case(scanner: Scanner, out: PrintStream) {
        var n = scanner.nextInt()
        var a = IntArray(n)
        var sum = 0
        for (i in 0..n-1) {
            a[i] = scanner.nextInt()
            sum++
        }

        var firstSpace = false;

        while (sum > 0) {
            if (firstSpace) out.print(' ')
            else firstSpace = true

            if (sum == 2) {
                for (i in 0..n-1) {
                    if (a[i] > 0) {
                        if (-- a[i] == 0) sum--;
                        out.print((i+'A'.toInt()).toChar())
                    }
                }

            } else {

                var max = 0
                for (i in 1..n-1) {
                    if (a[i] > a[max]) max = i
                }

                if (-- a[max] == 0) sum--;
                out.print((max+'A'.toInt()).toChar())
            }
        }
    }
}